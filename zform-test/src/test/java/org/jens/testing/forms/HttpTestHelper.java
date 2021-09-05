package org.jens.testing.forms;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Jens Ritter on 16/03/2021.
 */
public class HttpTestHelper {
    private final CloseableHttpClient closeableHttpClient;
    private final String baseurl;
    private final CookieStore cookieStore;

    public HttpTestHelper(int port) {
        cookieStore = new BasicCookieStore();
        this.closeableHttpClient = HttpClientBuilder.create()
            .setDefaultCookieStore(cookieStore)
            .disableAutomaticRetries()
            .disableRedirectHandling()
            .build();
        this.baseurl = "http://localhost:" + port;
    }

    public String path(String path) {
        return UriComponentsBuilder.fromHttpUrl(baseurl)
            .path(path)
            .toUriString();
    }

    public void login(String username, String password, String redirectTarget) throws IOException {
        HttpPost login = new HttpPost(path("/login"));

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));

        login.setEntity(new UrlEncodedFormEntity(params));
        try(CloseableHttpResponse loginResponse = closeableHttpClient.execute(login/*,httpContext*/)) {
            StatusLine statusLine = loginResponse.getStatusLine();
            assertThat(statusLine.getStatusCode()).isEqualTo(302);
            Header location = loginResponse.getFirstHeader("Location");
            assertNotNull(location);
            String value = location.getValue();
            assertThat(value).isEqualTo(path(redirectTarget));
        }
    }

    public void logout() throws IOException {
        try(CloseableHttpResponse post = post("/logout")) {
            assertRedirect(post, "/login?logout");
        }
    }

    public void assertRedirect(CloseableHttpResponse response, String target) throws IOException {
        StatusLine statusLine = response.getStatusLine();
        assertThat(statusLine.getStatusCode()).isEqualTo(302);
        Header location = response.getFirstHeader("Location");
        assertThat(location.getValue()).endsWith(target);
    }


    public CloseableHttpResponse get(String url) throws IOException {
        HttpGet get = new HttpGet(path(url));
        return closeableHttpClient.execute(get);
    }

    public CloseableHttpResponse post(String url) throws IOException {
        HttpPost get = new HttpPost(path(url));
        return closeableHttpClient.execute(get);
    }

    public ParameterBuilder postParameter(String url) {
        HttpPost post = new HttpPost(path(url));
        return new ParameterBuilder(closeableHttpClient, post);
    }

    public void is2xxOk(CloseableHttpResponse closeableHttpResponse) {
        StatusLine statusLine = closeableHttpResponse.getStatusLine();
        assertThat(statusLine.getStatusCode()).isEqualTo(200);
    }

    public void clearSession() {
        cookieStore.clear();
    }

    public static class ParameterBuilder {
        private final List<NameValuePair> params = new ArrayList<>();
        private final CloseableHttpClient client;
        private final HttpEntityEnclosingRequestBase request;

        public ParameterBuilder(CloseableHttpClient client, HttpEntityEnclosingRequestBase request) {
            this.client = client;
            this.request = request;
        }


        public ParameterBuilder param(String name, String value) {
            this.params.add(new BasicNameValuePair(name, value));
            return this;
        }

        public CloseableHttpResponse build() throws IOException {
            request.setEntity(new UrlEncodedFormEntity(params));
            return client.execute(request);
        }
    }
}


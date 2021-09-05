/*

<script type="text/javascript" th:src="@{/webjars/webforms-js/webforms.js}"></script>

 */
if (typeof (orgjenswebforms) === "undefined") {
    orgjenswebforms = {};
}

orgjenswebforms.build = function (element, relativeUrl) {
    "use strict";
    const protocol = window.location.protocol;
    const host = window.location.host;
    if (!relativeUrl.startsWith("/")) {
        relativeUrl = "/" + relativeUrl;
    }
    orgjenswebforms.apiurl = protocol + "//" + host + relativeUrl;
    orgjenswebforms.formid = element;
    // console.log("build#Init with Values:  ", orgjenswebforms.apiurl);
    return fetch(orgjenswebforms.apiurl)
        .then(response => response.json())
        .then(orgjenswebforms.buildform)
        .catch(orgjenswebforms.displayError);
};

orgjenswebforms.buildform = function (json) {
    "use strict";
    // console.log("buildform#Build-Form with ", json);
    // document.getElementById(orgjenswebforms.formid);
    $("#" + orgjenswebforms.formid).jsonForm({

        schema: json.schema,
        form: json.form,

        onSubmit: function (errors, values) {
            if (errors) {
                orgjenswebforms.displayError(errors, values, null);
            } else {
                // success committing
                // console.log(values);
                const form = document.getElementById(orgjenswebforms.formid);
                form.submit();
            }
            // var param = "";
            // for (const key in values) {
            //     const value = values[key];
            //     param += key + "=" + encodeURIComponent(values[key]) + "&";
            // }
            // console.log(param);
        }
    });
};

orgjenswebforms.displayError = function (a, b, c) {
    "use strict";
    const formdiv = document.getElementById(orgjenswebforms.formid);

    const div = document.createElement("div");
    div.classList = "alert alert-danger";
    div.role = "alert";
    formdiv.appendChild(div);

    div.textContent = "Error Requesting " + orgjenswebforms.apiurl + " for Form-Schema" + a;
    console.log("Error Requesting Form-Schema ", a, b, c);
};

// Register exports only for nodejs-runtime
if (typeof exports !== 'undefined') {
    if (typeof module !== 'undefined' && module.exports) {
        exports = module.exports = orgjenswebforms;
    }
}

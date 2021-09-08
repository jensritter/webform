/*

<script type="text/javascript" th:src="@{/webjars/webforms-js/webforms.js}"></script>

 */
if (typeof (orgjenswebform) === "undefined") {
    orgjenswebform = {};
}

orgjenswebform.build = function (element, relativeUrl) {
    "use strict";
    const protocol = window.location.protocol;
    const host = window.location.host;
    if (!relativeUrl.startsWith("/")) {
        relativeUrl = "/" + relativeUrl;
    }
    orgjenswebform.apiurl = protocol + "//" + host + relativeUrl;
    orgjenswebform.formid = element;
    // console.log("build#Init with Values:  ", orgjenswebform.apiurl);
    return fetch(orgjenswebform.apiurl)
        .then(response => response.json())
        .then(orgjenswebform.buildform)
        .catch(orgjenswebform.displayError);
};

orgjenswebform.buildform = function (json) {
    "use strict";
    // console.log("buildform#Build-Form with ", json);
    // document.getElementById(orgjenswebform.formid);
    $("#" + orgjenswebform.formid).jsonForm({

        schema: json.schema,
        form: json.form,

        onSubmit: function (errors, values) {
            if (errors) {
                orgjenswebform.displayError(errors, values, null);
            } else {
                // success committing
                // console.log(values);
                const form = document.getElementById(orgjenswebform.formid);
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

orgjenswebform.displayError = function (a, b, c) {
    "use strict";
    const formdiv = document.getElementById(orgjenswebform.formid);

    const div = document.createElement("div");
    div.classList = "alert alert-danger";
    div.role = "alert";
    formdiv.appendChild(div);

    div.textContent = "Error Requesting " + orgjenswebform.apiurl + " for Form-Schema" + a;
    console.log("Error Requesting Form-Schema ", a, b, c);
};

// Register exports only for nodejs-runtime
if (typeof exports !== 'undefined') {
    if (typeof module !== 'undefined' && module.exports) {
        exports = module.exports = orgjenswebform;
    }
}

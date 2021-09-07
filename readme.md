# webforms

uses jsonform https://github.com/jsonform/jsonform to display bootstrap-forms to be used from a Spring-Boot Application with Thymeleaf.

**_this is under heavy construction !!_**

## How to use :

### Add Dependency

    <dependency>
        <groupId>org.jens.webforms</groupId>
        <artifactId>webforms</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

Additional-Dependencies for JavaScript to work:

        <dependency>
            <groupId>org.webjars.bower</groupId>
            <artifactId>bootstrap</artifactId>
        </dependency>
        <dependency>
            <groupId>org.webjars.bower</groupId>
            <artifactId>jquery</artifactId>
        </dependency>

### Include into Thymeleaf-Page

    <div th:replace="org/jens/webforms/templates :: inline"></div>

### Assign to &lt;FORM&gt;-Element

Thymeleaf:

    <div class="container">
        <form id="form" class="form" th:method="post" th:action="@{/api/form}"></form>
    </div>

JS:

    $("#formid").jsonForm({

        schema: jsonform.schema,
        form: jsonform.form,

        onSubmit: function (errors, values) {
            if (errors) {
                orgjenswebforms.displayError(errors, values, null);
            } else {
                document.getElementById("form").submit();
            }

        });

### Assign onClick to custom Buttons

Modify the content of the js-variable "jsonform"

    jsonform.form[jsonform.form.length - 2].onClick = function (evt) {
        alert("hi");
    }

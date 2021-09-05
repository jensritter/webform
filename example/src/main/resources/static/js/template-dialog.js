document.addEventListener('DOMContentLoaded', function () {
    "use strict";
    const name = document.getElementById("name");
    name.addEventListener("click", function () {
        $('#dlg').modal('show');
    }, false);
});

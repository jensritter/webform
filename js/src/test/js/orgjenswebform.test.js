/**
 * Notwendige Jest-Einstellung, sodaß "require(jsdom)" und das setup durchgeführt wird :
 *
 * @jest-environment jsdom
 */


/**
 *
 *
 * Global Setup
 *
 *
 *
 */


beforeAll(function () {

    // setup jdom, damit window.location lesbar ist
    delete window.location;
    window.location = {
        protocol: "http:",
        host: "localhost:1"
    };

    // import Jquery
    const $ = require('jquery'); // jshint ignore:line
    global.$ = $;
    global.jQuery = $;

    // import jsonform:
    require("JSV");
    require("underscore");
    require("jsonform");

    // workaround: import node-fetch, da fetch() von nodejs nicht implementiert ist . . .
    fetch = require('node-fetch'); // jshint ignore:line
});

// Setup required for orgjenswebform.js :
let webform;
beforeEach(() => {
    // reset HTML-page
    document.body.innerHTML = "<html lang='en'><body><form id='formid'></form></body>";
    webform = require('../../main/resources/META-INF/resources/webjars/webforms-js/1.0-SNAPSHOT/orgjenswebform.js');
});


/**
 *
 * Helper Methods
 *
 *
 */

function loadJsonText() {
    const fs = require("fs");
    const txt = fs.readFileSync("../example/src/test/resources/org/jens/webforms/example/controller/api.json");
    expect(txt).toContain(125); // ascii f. {
    return txt;
}

function loadJson() {
    return JSON.parse(loadJsonText());
}


/**
 *
 *
 * Jdom-Tests :
 *
 *
 */
describe("jdom-tests", () => {

    describe('test setup', () => {
        test("mocked location", () => {
            expect(window.location.protocol).toBe("http:");
            expect(window.location.host).toBe("localhost:1");
        });

        test("valid html with form and id 'formid'", () => {
            const form = document.getElementById("formid");
            expect(form).not.toBeNull();
            expect(form.tagName).toBe("FORM");
        });
    });

    describe("webform", () => {
        test('display error on no-connection', () => {
            return webform.build("formid", "/form/api")
                .then(() => {
                    const form = document.getElementById("formid");
                    const errDiv = form.lastChild;
                    expect(errDiv).not.toBeNull();

                    expect(errDiv.tagName).toBe("DIV");
                    expect(errDiv.textContent).toContain("Error Requesting http://localhost:1/form/api for Form-SchemaFetchError");
                });
        });


        test('test load json response', () => {
            const json = loadJson();

            expect(json.schema).toBeDefined();
            expect(json.form).toBeDefined();

            expect(json.schema.name.type).toBe("string");
        });

        test('testbuild form from json', () => {
            const json = loadJson();
            webform.buildform(json);

            const formid = document.getElementById("formid");
        });

    });

});


/**
 *
 *
 * Integration - Tests
 *
 *
 */
describe("integration-tests", () => {
    let server;
    let port;
    beforeAll(() => {
        const express = require("express");
        const app = express();

        app.get("/form/api", (req, res) => {
            const json = loadJsonText();
            res.send(json);
        });

        server = app.listen();
        port = server.address().port;
        window.location.host = "localhost:" + port;
        return server;
    });

    afterAll(done => {
        server.close();
        done();
    });

    describe("test setup", () => {
        test("check http-server", () => {
            return fetch(window.location.protocol + "//" + window.location.host).then((response) => {
                expect(response.status).toBe(404);
            });
        });

        test("check form-api", () => {
            return fetch(window.location.protocol + "//" + window.location.host + "/form/api").then((response) => {
                expect(response.status).toBe(200);
            });
        });

        test("check window.location", () => {
            expect(window.location.host).toBe("localhost:" + port);
            expect(window.location.protocol).toBe("http:");
        });
    });

    describe("webform", () => {
        test('display no error', () => {
            return webform.build("formid", "/form/api")
                .then(() => {
                    // kontrolle, das "submit" als letztes element in der form vorhanden ist
                    const form = document.getElementById("formid");
                    expect(form).not.toBeNull();

                    const jsdiv = form.lastElementChild;
                    expect(jsdiv).not.toBeNull();

                    const submit = jsdiv.lastElementChild;
                    expect(submit).not.toBeNull();

                    expect(submit.value).toBe("Submit");
                    expect(submit.tagName).toBe("INPUT");
                    expect(submit.type).toBe("submit");

                });
        });

        test("html-form is correctly build", () => {
            return webform.build("formid", "/form/api")
                .then(() => {
                    const form = document.getElementById("formid");
                    expect(form).not.toBeNull();

                    const jsdiv = form.lastElementChild;
                    expect(jsdiv).not.toBeNull();

                    for (const formgroup of jsdiv.children) {

                        if (formgroup.tagName === "INPUT") {
                            expect(formgroup.type).toBe("submit");
                            expect(formgroup.value).toBe("Submit");
                            expect(formgroup.className).toBe("btn btn-primary ");
                        } else {
                            let hit = false;
                            if (formgroup.classList.contains("jsonform-error-name")) {
                                hit = expectTextField(formgroup, "text", "name", "Name", "description-2", true);
                            }
                            if (formgroup.classList.contains("jsonform-error-vorname")) {
                                hit = expectTextField(formgroup, "text", "vorname", "Vorname", "description-3", false);
                            }
                            if (formgroup.classList.contains("jsonform-error-strasse")) {
                                hit = expectTextField(formgroup, "text", "strasse", "Straße", "description-4", false);
                            }
                            if (formgroup.classList.contains("jsonform-error-birthday")) {
                                hit = expectTextField(formgroup, "date", "birthday", "Geburtstag", "description-5", false);
                            }
                            if (formgroup.classList.contains("jsonform-error-heute")) {
                                const eingabefeld = expectField(formgroup, "Ist es heute", false);
                                expect(eingabefeld.classList).toContain("checkbox");

                                const lbl = eingabefeld.firstChild;
                                expect(lbl.textContent).toBe("ja/nein");
                                const chk = lbl.firstChild;
                                expect(chk.type).toBe("checkbox");
                                expect(chk.name).toBe("heute");
                                expect(chk.selected).not.toBeDefined();

                                hit = expectDescription(eingabefeld, "description-6");
                            }
                            if (formgroup.classList.contains("jsonform-error-ort")) {

                                const eingabefeld = expectField(formgroup, "Orte", false);
                                expect(eingabefeld.tagName).toBe("SELECT");
                                expect(eingabefeld.name).toBe("ort");
                                expect(eingabefeld.required).toBe(false);
                                expect(eingabefeld.classList).toContain("form-control");

                                const options = eingabefeld.getElementsByTagName("OPTION");
                                expect(options.length).toBe(4);

                                expect(options[0].value).toBe("0");
                                expect(options[0].textContent).toBe("Hannover");

                                expect(options[1].value).toBe("1");
                                expect(options[1].textContent).toBe("Goslar");

                                expect(options[2].value).toBe("2");
                                expect(options[2].textContent).toBe("Bad Tölz");

                                expect(options[3].value).toBe("3");
                                expect(options[3].textContent).toBe("Hamburg");

                                hit = expectDescription(eingabefeld, "description-1");
                            }

                            if (!hit) {
                                expect("").toBe(formgroup.outerHTML);
                            }
                        }
                    }
                });
        });

    });

    function expectTextField(formgroup, type, name, labelText, description, required) {
        const eingabefeld = expectField(formgroup, labelText, required);
        expect(eingabefeld).not.toBeNull();
        expect(eingabefeld.type).toBe(type);
        expect(eingabefeld.name).toBe(name);
        expect(eingabefeld.required).toBe(required);
        expect(eingabefeld.classList).toContain("form-control");

        return expectDescription(eingabefeld, description);
    }

    function expectField(formgroup, labelText, required) {
        if (required) {
            expect(formgroup.classList).toContain("jsonform-required");
        } else {
            expect(formgroup.classList).not.toContain("jsonform-required");
        }
        const label = formgroup.firstChild;
        expect(label).not.toBeNull();
        expect(label.textContent).toBe(labelText);

        const controls = label.nextSibling;
        expect(controls).not.toBeNull();

        const eingabefeld = controls.firstChild;
        expect(eingabefeld).not.toBeNull();
        return eingabefeld;
    }

    function expectDescription(eingabefeld, description) {
        const desc = eingabefeld.nextSibling;
        expect(desc.textContent).toBe(description);

        const errors = desc.nextSibling;
        expect(errors).not.toBeNull();
        expect(errors.textContent).toBe("");

        expect(errors.nextSibling).toBeNull();

        return true;
    }
});




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

// Setup required for orgjenswebforms.js :
let webform;
beforeEach(() => {
    // reset HTML-page
    document.body.innerHTML = "<html lang='en'><body><form id='formid'></form></body>";
    webform = require('../../main/resources/META-INF/resources/webjars/webforms-js/1.0-SNAPSHOT/orgjenswebforms.js');
});


/**
 *
 * Helper Methods
 *
 *
 */

function loadJsonText() {
    const fs = require("fs");
    const txt = fs.readFileSync("src/test/js/api.json");
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
    });
});




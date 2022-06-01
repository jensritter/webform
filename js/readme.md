# learn-module f. npm+js
node-verwalter ( für windows ) nvm https://github.com/nvm-sh/nvm

## NPM und der ganze müll.

| cmd                          | bemerkung                                    |
|------------------------------|----------------------------------------------|
| npm init                     | Erstellt leer es package.json                |
| npm install --save-dev jsdom | add 'jsdom' to dev-dependency                |
| npm install jsdom            | add 'jsdom' to dependency                    |
| npm run test                 | ruft den testrunner von "scripts":"test" auf |
| npm install                  | install/upgrades dependencies and updates package-lock.json |   
| npm ci                       | install dependencies - does not touch package-lock.json | 
| npm prune                    | löscht nicht mehr benötige packages          |

Als TestRunner habe ich "jest" in packages.json konfiguriert

    "scripts": {
        "test": "jest"
    },

Die Konfiguration von "jest" in packages.json angepasst:

    "jest": {
        "roots": [
            ".",
            "src/test/js"
        ],
        "setupFiles": [
            "<rootDir>/src/test/setup/jest-config-fail-always.js"
        ],
        "testResultsProcessor": "jest-junit",
        "reporters": [
            "default",
            "jest-junit"
        ]
        "collectCoverage": true,
        "coverageReporters": [
            "text",
            "cobertura"
        ],
        "coverageDirectory": "target/coverage",
    },
    "jest-junit": {
        "outputDirectory": "target/surefire-reports",
        "outputName": "jest-junit.xml"
    }

Damit die Test-Dateien gefunden werden, via "roots" zusätzlich den pfad "src/test/js" hinterelgt.

(Geht das wirklich?) Damit ich bei unbehandelten Promise(n) eine warnung bekomme, habe ich ein script im Internet gefunden, und dies über "setupFiles" hinterlegt.

Damit Jenkins auch die Test-Ergebnisse bekommt, habe ich "jest-junit" als dev-Package installiert, und konfiguriert. Bei "reporters" zusätzlich "jest-junit" hinzugefügt. Bei "testResultsProcessor"
habe ich "jest-junit" hinterlegt.
(TODO: eins von beiden ggf. löschen ? )

Damit die Coverage erstellt, und für jenkins gespeichert wird, ist "collectCoverage", "coverageReporters" und "coverageDirectory" notwendig gewesen.

Ein eingestellter Versuch war, jest ohne Cache-Verzeichnis zu betreiben, bzw. das Verzeichnis vorher zu löschen. -- Hat sich aber alles nicht wirklich ausgewirkt.

    "scripts": {
        "pretest": "jest --clearCache",
        "test": "jest --ci --no-cache --verbose"
    },

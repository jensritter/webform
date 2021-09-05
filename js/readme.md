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



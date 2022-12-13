pipeline {
    agent any
//    agent { label 'windows' }
    triggers {
      snapshotDependencies()
      pollSCM '@hourly'
    }

    stages {
        stage("build") {
            stages {
                stage("build-unix") {
                    when { expression { return isUnix() } }
                    steps {
                        withMaven(
                		    jdk: "${env.DEFAULT_JDK}", 
            				maven: "${env.DEFAULT_MAVEN}", 
                            mavenLocalRepo: '.repository'
        			    ) {
                		    sh "mvn clean verify sonar:sonar deploy"
        			    }
                    }
                }
                stage("build-notunix") {
                when { expression { return !isUnix() } }
                    steps {
                        withMaven(
                		    jdk: "${env.DEFAULT_JDK}",
            				maven: "${env.DEFAULT_MAVEN}",
                            mavenLocalRepo: '.repository'
        			    ) {
                		    bat "mvn clean verify sonar:sonar deploy"
        			    }
                    }
                }
            }
            post {
                cleanup {
                 dir('.repository') { deleteDir() } 
                }
                always {
                    recordIssues(tools: [java(), mavenConsole(), /* kotlin() */ ])
                }
            }
        }
    }
    post {
        always {
            jabberNotify notificationStrategy: 'new failure and fixed', targets: 'ritter@chat.mitegro.net'
        }
    }
}

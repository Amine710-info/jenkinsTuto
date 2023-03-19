pipeline {
    agent any

    triggers { pollSCM('* * * * *')
    }
    stages {
        stage('checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Amine710-info/jenkinsTuto.git'
            }
        }
        stage('Build') {
            steps {
                // Run Maven on a Unix agent.
          //      sh './mvnw clean package'
        sh 'true'
            // To run Maven on a Windows agent, use
            // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                always  {
                //    junit '**/target/surefire-reports/TEST-*.xml'
                  //  archiveArtifacts 'target/*.jar'
                /* }
                 changed { */
                   emailext attachLog: true,  
                    body: "Please go to (build ${BUILD_URL}) ", 
                    recipientProviders: [requestor(), upstreamDevelopers()],
                    to: 'test@jenkins',
                    subject:  "Job \'${JOB_NAME}\' (${BUILD_NUMBER}) ,with status ${currentBuild.result}"
                }
            }
        }
    }
}

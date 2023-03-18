pipeline {
    agent any

  
    stages {
        stage('checkout'){
            steps{
             git branch: 'main', url: 'https://github.com/Amine710-info/jenkinsTuto.git'
            }
        }
        stage('Build') {
            steps {
        
                // Run Maven on a Unix agent.
            sh "./mvnw clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
               
                always  {
                    junit '**/target/surefire-reports/TEST-*.xml'
                  archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}

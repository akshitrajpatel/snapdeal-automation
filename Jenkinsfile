pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'JDK21'  // Make sure this matches the name you gave in Global Tool Configuration
    }

    stages {
        stage('Verify JAVA_HOME') {
            steps {
                bat 'echo %JAVA_HOME%'
                bat 'java -version'
            }
        }
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
    }

    post {
        failure {
            echo "Build failed. Email notification would be sent here."
        }
    }
}

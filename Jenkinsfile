pipeline {
    agent any
    environment {
        CHROME_DRIVER_HOME = "C:\Tools\chromedriver_win32" // Optional, as WebDriverManager handles this
    }
    tools {
        maven 'maven' // Maven setup in Jenkins global configuration
		jdk 'JDK21'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Build the Maven project
                sh 'mvn clean install'
            }
        }
        stage('Run Tests') {
            steps {
                // Execute the tests
                sh 'mvn test'
            }
        }
        stage('Generate Reports') {
            steps {
                // Publish test results and HTML report (if generated)
                junit '**/target/surefire-reports/*.xml'
                publishHTML(target: [
                    reportDir: 'target/site',
                    reportFiles: 'index.html',
                    reportName: 'Test Report'
                ])
            }
        }
    }
    post {
        always {
            // Archive build artifacts
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            cleanWs() // Clean workspace after build
        }
    }
}

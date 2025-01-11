pipeline {
    agent any

    environment {
        // Define global environment variables
        SONARQUBE_SERVER = 'SonarQubeServer'
        DOCKER_IMAGE = 'rhtaf-image'
        DOCKER_CONTAINER = 'rhtaf-container'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code from Git repository...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project using Maven...'
                bat 'mvn clean install'
            }
        }

        stage('Static Code Analysis') {
            steps {
                echo 'Running static code analysis using SonarQube...'
                withSonarQubeEnv('SonarQubeServer') {
                    bat 'mvn sonar:sonar'
                }
            }
        }

        stage('Run Tests') {
            parallel {
                stage('Unit Tests') {
                    steps {
                        echo 'Running unit tests...'
                        bat 'mvn test'
                    }
                }

                stage('UI Tests with Selenium') {
                    steps {
                        echo 'Running UI tests with Selenium...'
                        bat 'mvn exec:java -Dexec.mainClass="com.example.selenium.tests.MainTest"'
                    }
                }
            }
        }

        stage('Dynamic Code Analysis') {
            steps {
                echo 'Collecting test metrics with SonarQube...'
                script {
                    def qualityGate = waitForQualityGate()
                    if (qualityGate.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qualityGate.status}"
                    }
                }
            }
        }

        stage('Package Docker Image') {
            steps {
                echo 'Packaging application into a Docker image...'
                bat """
                docker build -t ${DOCKER_IMAGE} .
                docker tag ${DOCKER_IMAGE} your-dockerhub-akshit2211/${DOCKER_IMAGE}:latest
                """
            }
        }

        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker image to registry...'
                withCredentials([string(credentialsId: 'docker-hub-token', variable: 'DOCKER_HUB_TOKEN')]) {
                    bat """
                    echo ${DOCKER_HUB_TOKEN} | docker login -u your-dockerhub-akshit2211 --dckr_pat_1IGd8eh6Mz96-J8cojlUiNVKKWs-stdin
                    docker push your-dockerhub-username/${DOCKER_IMAGE}:latest
                    """
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application to the staging environment...'
                bat """
                docker run --name ${DOCKER_CONTAINER} -d -p 8080:8080 ${DOCKER_IMAGE}
                """
            }
        }
    }

    post {
        always {
            echo 'Cleaning up resources...'
            bat 'docker stop ${DOCKER_CONTAINER} || exit 0'
            bat 'docker rm ${DOCKER_CONTAINER} || exit 0'
        }
        success {
            echo 'Pipeline executed successfully.'
        }
        failure {
            echo 'Pipeline failed. Notifying team...'
            mail to: 'aksh.patel78@gmail.com',
                 subject: "Pipeline Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "The pipeline has failed. Please review the logs at ${env.BUILD_URL}."
        }
    }
}

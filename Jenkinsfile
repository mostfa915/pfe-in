pipeline {
    agent any

    environment {
        BACKEND_IMAGE = "mostfa123/p-f-e-v2:latest"
        FRONTEND_IMAGE = "mostfa123/frontend-app-v1:latest"
        DOCKERHUB_CREDENTIALS = credentials('dockerhub') // Défini dans Jenkins
    }

    stages {

        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/TON-UTILISATEUR/TON-REPO.git', branch: 'main'
            }
        }

        stage('Build Backend') {
            steps {
                dir('p-f-e-v2') {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend-app-v1') {
                    sh 'npm install'
                    sh 'npm run build -- --configuration production'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker build -t $BACKEND_IMAGE ./p-f-e-v2'
                sh 'docker build -t $FRONTEND_IMAGE ./frontend-app-v1'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withDockerRegistry([credentialsId: "$DOCKERHUB_CREDENTIALS", url: ""]) {
                    sh 'docker push $BACKEND_IMAGE'
                    sh 'docker push $FRONTEND_IMAGE'
                }
            }
        }
    }
}


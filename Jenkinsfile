pipeline {
    agent any

    environment {
        BACKEND_IMAGE = "mostfa123/p-f-e-v2:latest"
        FRONTEND_IMAGE = "mostfa123/frontend-app-v1:latest"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/mostfa915/pfe-in.git',
                    branch: 'main'
            }
        }

        stage('Test Backend') {
            steps {
                dir('p-f-e-v2') {
                    echo "🧪 Exécution des tests unitaires backend..."
                    sh './mvnw test'
                }
            }
        }

        stage('Build Backend') {
            steps {
                dir('p-f-e-v2') {
                    echo "🔨 Build du backend Spring Boot..."
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend-app-v1') {
                    echo "🧱 Build du frontend Angular..."
                    sh 'npm install'
                    sh 'npm run build -- --configuration production'
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    echo "🐳 Build Docker Images..."
                    sh 'docker --version'
                    sh "docker build -t $BACKEND_IMAGE ./p-f-e-v2"
                    sh "docker build -t $FRONTEND_IMAGE ./frontend-app-v1"
                }
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PWD'
                )]) {
                    sh '''
                        echo "🔐 Login to Docker Hub..."
                        echo "$DOCKER_PWD" | docker login -u "$DOCKER_USER" --password-stdin
                    '''
                }
            }
        }

        stage('Docker Push') {
            steps {
                echo "📤 Pushing Docker images..."
                sh "docker push $BACKEND_IMAGE"
                sh "docker push $FRONTEND_IMAGE"
            }
        }
    }

    post {
        always {
            echo "🧹 Nettoyage..."
            sh 'docker rmi $BACKEND_IMAGE || true'
            sh 'docker rmi $FRONTEND_IMAGE || true'
        }
    }
}



pipeline {
    agent any
    tools {
        jdk 'jdk21'
    }

    stages {
        stage('Сборка') {
            steps {
                sh 'echo $JAVA_HOME'
                sh 'java -version'
                sh 'chmod +x mvnw'
                sh './mvnw clean install'

            }
        }

        stage('Сборка Docker образа') {
            when {
                branch 'master'
            }
            steps {
                sh 'docker build -t steadydev/themeeight .'
            }
        }

        stage('Push в Docker Hub') {
            when {
                branch 'master'
            }
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh 'docker push steadydev/themeeight'
                }
            }
        }
    }
}
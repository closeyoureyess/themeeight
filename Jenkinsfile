pipeline {
    agent any // можно запускать на любом доступном агенте

    stages {
        stage('Сборка') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Сборка Docker образа') {
            when {
                branch 'master'
            }
            steps {
                sh 'docker build -t твой_логин/имя_проекта .'
            }
        }

        stage('Push в Docker Hub') {
            when {
                branch 'master'
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh 'docker push steadydev/themeeight-service'
                }
            }
        }
    }
}
pipeline {
    agent any
    tools {
        jdk 'jdk21'
    }
    environment {
        TESTCONTAINERS_RYUK_DISABLED = "true"
        TESTCONTAINERS_HOST_OVERRIDE = 'host.docker.internal'
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

        stage('Этап 2') {
            steps {
                    sh 'echo Current branch is: $BRANCH_NAME'
            }
        }
    }
}
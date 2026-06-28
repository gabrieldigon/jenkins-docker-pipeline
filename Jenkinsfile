pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
    }

    triggers {
        cron('* * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                deleteDir()
                git branch: 'main', url: 'https://github.com/gabrieldigon/jenkins-docker-pipeline.git'
            }
        }

        stage('Build em container Docker') {
            agent {
                docker {
                    image 'maven:3.9.9-eclipse-temurin-21'
                    reuseNode true
                    args '-v maven-repo:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
                sh 'sleep 10'
            }
        }

        stage('Testes em outro container Docker') {
            agent {
                docker {
                    image 'maven:3.9.9-eclipse-temurin-21'
                    reuseNode true
                    args '-v maven-repo:/root/.m2'
                }
            }
            steps {
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    sh 'mvn test'
                }
                sh 'sleep 10'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline finalizado com sucesso.'
        }

        unstable {
            echo 'Pipeline finalizado como instavel: build compilou, mas algum teste falhou.'
        }

        failure {
            echo 'Pipeline falhou durante o processo.'
        }
    }
}
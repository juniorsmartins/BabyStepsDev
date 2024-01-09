pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Fase de verificação'
                checkout scm

                echo "Java VERSION"
                sh 'java -version'

                echo "Maven VERSION"
                sh 'mvn -version'
            }
        }

        stage('Pull Configurations') {
            steps {
                echo 'Fase de configuração'

             }
        }

        stage('Build') {
            steps {
                echo 'Fase de construção'
                git branch: 'master', url: 'https://github.com/juniorsmartins/site.git'
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Fase de teste'
                sh 'mvn test'
            }
            post {
                failure {
                    mail to: 'juniorsoaresmartins@gmail.com',
                        subject: 'A pipeline falhou! Verifique os testes automatizados.',
                        body: 'Falha nos testes automatizados'
                }
            }
        }

        stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS'
              }
            }
            steps {
                echo 'Deploying....'
            }
        }
    }
}


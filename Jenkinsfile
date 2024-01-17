pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-21'
        }
    }
    stages{
        stage('Versões') {
            steps {
                echo "Java VERSION"
                sh 'java -version'

                echo "Maven VERSION"
                sh 'mvn -version'

                echo "Git VERSION"
                sh 'git --version'
            }
        }
        stage('Clone Repository') {
            steps {
                echo 'clonando repositório...'
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'e426aa02-6bce-4e47-9792-89f0655ed2c2', url: 'https://github.com/juniorsmartins/site']])
            }
        }
        stage('Build Maven Project') {
            steps {
                echo 'limpando e construíndo projeto'
                sh 'mvn clean install'
            }
        }
        stage('Tests') {
            steps {
                echo 'rodando testes automatizados'
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
    }
}


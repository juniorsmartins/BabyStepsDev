pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/local/openjdk-21'
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
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'ad0a0b0c-4025-495d-ab95-98bef81e09a3', url: 'https://github.com/juniorsmartins/site']])
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
        }
    }
}


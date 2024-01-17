pipeline {
    agent any

//     environment {
//         JAVA_HOME = '/usr/local/openjdk-21'
//     }
    stages{
        stage('Versões') {
            steps {
                echo "Java VERSION"
                sh 'java -version'

//                 echo "Maven VERSION"
//                 sh 'mvn -version'

                echo "Git VERSION"
                sh 'git --version'
            }
        }
        stage('Clone Repository') {
            steps {
                echo 'clonando repositório...'
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: '2699083d-6c9c-44d5-81c8-c7466c08e54f', url: 'https://github.com/juniorsmartins/site']])
            }
        }
        stage('Build Maven Project') {
            steps {
                echo 'limpando e construíndo projeto'
//                 sh 'mvn clean install'
                sh 'mvn clean install -Dmaven.home=MAVEN'
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


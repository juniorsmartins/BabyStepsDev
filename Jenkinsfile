pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'MAVEN'
        PATH = "$MAVEN_HOME/bin:$PATH"
    }

    tools {
        git 'Git'
    }

    stages{
        stage('1 - Version Declaration') {
            steps {
                echo "Java VERSION"
                sh 'java -version'

                echo "Maven VERSION"
                sh 'mvn -version'

                echo "Git VERSION"
                sh 'git --version'
            }
        }
        stage('2 - Clone Project from GitHub') {
            steps {
                echo 'clonando repositório...'
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: '2699083d-6c9c-44d5-81c8-c7466c08e54f', url: 'https://github.com/juniorsmartins/site']])
            }
        }
        stage('3 - Build Maven Project') {
            steps {
                echo 'limpando e construíndo projeto...'
                sh 'mvn clean install'
            }
        }
        stage('4 - Run Automated Tests') {
            steps {
                echo 'rodando testes automatizados...'
                sh 'mvn test'
            }
        }
        stage('5 - Build Docker Images and swap Containers') {
            steps {
                echo 'construíndo imagens Docker do projeto e substituíndo containers...'
            }
        }
        stage('6 - Deploy') {
            steps {
                echo 'deploy...'
            }
        }
        stage('7 - Update Images on DockerHub') {
            steps {
                echo 'atualizando imagens no DockerHub...'
            }
        }
    }
    post {
        always {
            echo 'Finish'
//             sh 'docker logout'
            deleteDir()
            cleanWs()
        }
    }
}


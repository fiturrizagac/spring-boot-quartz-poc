pipeline {

    agent {
        docker {
            image 'maven:3.3.9-jdk-8-alpine'
            args '-v $HOME/.m2:/root/.m2'
        }
    }

    node {
        stage("Test") {
            checkout scm
            sh 'mvn -v'
            sh "mvn clean test"
        }
    }



}
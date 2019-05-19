node {

    agent {
        docker {
            image 'maven:3.3.9-jdk-8-alpine'
        }
    }

    stage("Test") {
        checkout scm
        sh 'mvn -v'
        sh "mvn clean test"
    }

}
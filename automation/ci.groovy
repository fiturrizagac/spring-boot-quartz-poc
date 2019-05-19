node {

    checkout scm

    stage("Test") {

        docker.image('maven:3.3.9-jdk-8-alpine')
                .withRun('-v $HOME/.m2:/root/.m2') {

            sh 'mvn -v'
            sh "mvn clean test"

        }


    }

}
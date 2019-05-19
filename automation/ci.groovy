node {

    stage("Test") {
        checkout scm
        sh "mvn clean test"
        // sonar
        // fortify
    }

}
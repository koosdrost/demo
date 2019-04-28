pipline {

    agent any

    tools {
        maven 'maven-3.6.0'
        jdk 'JDK9'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

    }

}

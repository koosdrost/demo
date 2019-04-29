pipeline {

  agent any

  tools {
    maven '3.6.0'
    java 'JDK9'
  }

  stages {
    stage('Build') {
        steps {
            sh 'mvn clean install'
        }
     }
  }

}

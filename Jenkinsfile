pipeline {

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

     stage('Nexus') {
        steps {
          nexusArtifactUploader artifacts: [[artifactId: 'demo', classifier: '', file: '/Users/Shared/Jenkins/Home/workspace/demo/target/demo-1.0.0-SNAPSHOT.war', type: 'war']], credentialsId: 'nexus', groupId: 'com.example', nexusUrl: 'localhost:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '1.0.0-SNAPSHOT'
        }
     }

  }

}

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

      stage('SonarQube') {
                 // stage-specifieke omgevingsvar
                 environment {
                     scannerHome = tool 'SonarQubeScanner'
                 }

                 steps {
                    withSonarQubeEnv('sonar') {
                        sh "${scannerHome}/bin/sonar-scanner"
                    }
                 }
       }

         stage('Owasp Dependency Check') {
                   steps {
                       dependencyCheckAnalyzer datadir: '/Users/Shared/Jenkins/Home/workspace/dependencyDatabase/dependency-check-data', hintsFile: '', includeCsvReports: false, includeHtmlReports: false, includeJsonReports: false, includeVulnReports: false, isAutoupdateDisabled: false, outdir: '', scanpath: '', skipOnScmChange: false, skipOnUpstreamChange: false, suppressionFile: '', zipExtensions: ''
                       dependencyCheckPublisher canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '', unHealthy: ''
                   }
               }

  }

}

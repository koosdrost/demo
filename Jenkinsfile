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


    stage("Quality checks") {
        parallel {
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

    stage('Performance analysis') {
               steps {
                   sh 'mvn gatling:test'
                   // Archive results for Jenkins visualization
                   gatlingArchive()
               }
    }

    stage('Deploy') {
           steps {
                input "Je gaat deployen, is dat OK?"
                sh "mvn tomcat7:redeploy -Dtomcat.username=admin -Dtomcat.password=password"
            }
    }

  }

}

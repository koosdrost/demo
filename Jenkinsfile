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
            steps{
               nexusArtifactUploader artifacts: [[artifactId: 'demo', classifier: '', file: '/Users/Shared/Jenkins/Home/workspace/demo/target/demo-1.0.0.jar', type: 'war']], credentialsId: 'nexus', groupId: 'com.example', nexusUrl: 'localhost:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-releases', version: '1.0.0'
            }
        }

        stage('Sonar') {
          steps{   }
        }

        stage('OWASP Dependency Check') {
            steps {
                dependencyCheckAnalyzer datadir: '/Users/Shared/Jenkins/Home/workspace/dependencyDatabase/dependency-check-data', hintsFile: '', includeCsvReports: false, includeHtmlReports: false, includeJsonReports: false, includeVulnReports: false, isAutoupdateDisabled: false, outdir: '', scanpath: '', skipOnScmChange: false, skipOnUpstreamChange: false, suppressionFile: '', zipExtensions: ''
            }
        }

    }
    post {
        always {
            dependencyCheckPublisher canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '', unHealthy: ''
        }
    }
}

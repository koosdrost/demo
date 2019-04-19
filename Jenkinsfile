pipeline {
    agent any
    tools {
        maven 'maven-3.6.0'
        jdk 'JDK9'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn verify'
            }
        }
        stage('OWASP Dependency Check') {
            steps {
                dependencyCheckAnalyzer datadir: '', hintsFile: '', includeCsvReports: false, includeHtmlReports: true, includeJsonReports: false, includeVulnReports: false, isAutoupdateDisabled: true, outdir: '', scanpath: '', skipOnScmChange: false, skipOnUpstreamChange: false, suppressionFile: '', zipExtensions: ''
            }
        }
    }
    post {
        always {
            dependencyCheckPublisher canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '', unHealthy: ''
        }
    }
}

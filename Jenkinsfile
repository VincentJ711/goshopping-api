pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'mvn package'
      }
    }

    stage('deploy') {
      when {
        branch 'dev'
      }

      steps {
        sh 'cp target/api.war /usr/share/tomcat/webapps/goshopping-api.war'
      }
    }
  }
}
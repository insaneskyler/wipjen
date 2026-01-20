pipeline {
  agent any

  tools {
    jdk 'Java21'
    maven 'Maven'
  }

  environment {
    DOCKER_USER = "jaianshulgautam"
    IMAGE_NAME = "indiaproj"
    IMAGE_TAG = "1.0"
  }

  stages {

    stage('Checkout Code') {
      steps {
        git branch: 'main',
            url: 'https://github.com/insaneskyler/k8-test.git'
      }
    }

    stage('Build Project') {
      steps {
        bat 'mvn clean package'
      }
    }

    stage('Build Docker Image') {
      steps {
        bat 'docker build -t %DOCKER_USER%/%IMAGE_NAME%:%IMAGE_TAG% .'
      }
    }

    stage('Push Docker Image to DockerHub') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhubpwd', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
          bat '''
          docker login -u %USER% -p %PASS%
          docker push %DOCKER_USER%/%IMAGE_NAME%:%IMAGE_TAG%
          '''
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        bat '''
        kubectl apply -f deployment.yaml
        kubectl apply -f services.yaml
        kubectl get pods
        kubectl get services
        minikube addons enable dashboard
        minikube dashboard
        '''
      }
    }
  }

  post {
    success {
      echo 'CI/CD Pipeline Executed Successfully!'
    }
    failure {
      echo 'Pipeline Failed — Check Logs'
    }
  }
}

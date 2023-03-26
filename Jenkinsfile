pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('checkout') {
            // Get some code from a GitHub repository
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sharang1996/favourite-movies']])
            }
        }
        stage('package') {
            // install dependencies and create jar.
            steps {
                sh 'mvn clean install package'
            }
        }
        stage('build') {
            // Build a docker image.
            steps {
                sh 'mvn spring-boot:build-image -Dspring-boot.build-image.imageName=sharanggupta/favouritemovies'
            }
        }
        stage('push') {
            // Push image to dockerhub
            steps {
                withCredentials([string(credentialsId: 'dockerhubtoken', variable: 'dockerhubtoken')]) {
                    sh 'docker login -u sharanggupta -p ${dockerhubtoken}'
                }
                sh 'docker push sharanggupta/favouritemovies'
            }
        }
        stage('deploy') {
            // Deploy to cluster
            steps {
                sh 'kubectl apply -f kubernetes/'
            }
        }
    }
}

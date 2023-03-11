pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('checkout') {
            steps {
                // Get some code from a GitHub repository
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sharang1996/favourite-movies']])
            }
        }
        stage('package') {
            steps {
                // install dependencies and create jar.
                maven clean install package
            }
        }
        stage('build') {
            steps {
                // Build a docker image.
                maven spring-boot:build-image -Dspring-boot.build-image.imageName=latestfavouritemovies
            }
        }
    }
}

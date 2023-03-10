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
                // Run Maven on a Unix agent.
                sh 'mvn clean install package'
            }
        }
        stage('build') {
            steps {
                // Run Maven on a Unix agent.
                sh 'docker build -t favouritemovies .'
                sh 'ls'
            }
        }
    }
}

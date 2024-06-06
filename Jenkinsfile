pipeline {
    agent any

    environment {
        namespace = "${namespace}"
        dockerImage = "${image}"
        deployment = "${deployment}"
    }

    tools {
        maven 'MAVEN_JENKINS'
    }

    stages {
        stage('Initialize')
        {
            def dockerHome = tool 'DOCKER_JENKINS'
            def mavenHome  = tool 'MAVEN_JENKINS'
            env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
        }
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit Tests') {
            steps {
                echo 'Running unit tests...'
                // Uncomment the line below if you want to run unit tests
                // sh 'mvn test'
            }
        }
        stage('Deploy to GitLab') {
            steps {
                echo 'Deploying to GitLab...'
                sh 'docker login registry.gitlab.com -u odsdaniel99@gmail.com -p Dsol?2603'
                sh 'docker build --no-cache -t registry.gitlab.com/all-docker/image/${dockerImage} .'
                sh 'docker push registry.gitlab.com/all-docker/image/${dockerImage}'
                sh 'docker rmi registry.gitlab.com/all-docker/image/${dockerImage}'
                sh 'rm -rf *'
                sh 'docker builder prune --force'
            }
        }
        stage('Update Kubernetes Cluster') {
            steps {
                echo 'Updating Kubernetes cluster...'
                sh 'kubectl rollout restart deployment/${deployment} --namespace=${namespace}'
            }
        }
    }
}

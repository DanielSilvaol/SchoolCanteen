pipeline {
    agent any

    environment {
        namespace = "${namespace}"
        dockerImage = "${image}"
        deployment = "${deployment}"
    }

    tools {
        maven 'MAVEN_JENKINS'
        'docker' 'DOCKER_JENKINS'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'mvn clean package -DskipTests=true'
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

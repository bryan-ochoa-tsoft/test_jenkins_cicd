pipeline {
    agent any

    triggers {
        pollSCM('* * * * *')
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Obteniendo código desde GitHub..."
            }
        }

        stage('Prepare Artifact') {
            steps {
                sh '''
                echo "archivo CI/CD desde Jenkins - build $(date)" > artifact.txt
                '''
            }
        }

        stage('Deploy to Pivote') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'ssh-lab-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {

                    sh '''
                    echo "Desplegando en pivote..."

                    sshpass -p $PASS scp -P 2222 -o StrictHostKeyChecking=no artifact.txt $USER@pivote:/tmp/

                    sshpass -p $PASS ssh -p 2222 -o StrictHostKeyChecking=no $USER@pivote \
                    "echo 'Archivo recibido:' && cat /tmp/artifact.txt"
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Deploy completado correctamente"
        }
        failure {
            echo "❌ Algo falló en el pipeline"
        }
    }
}

pipeline {
    agent any

    triggers {
        pollSCM('* * * * *') // simple polling (luego webhook si quieres)
    }

    stages {
        stage('Test SSH via Bastion') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'ssh-lab-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    sh """
                    echo "Probando conexión vía pivote..."

                    sshpass -p $PASS ssh -o StrictHostKeyChecking=no $USER@pivote \\
                    "sshpass -p $PASS ssh -o StrictHostKeyChecking=no $USER@final \\
                    'echo Conectado correctamente al servidor final'"
                    """
                }
            }
        }

        stage('Copy File') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'ssh-lab-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {

                    sh """
                    echo "archivo CI/CD" > test.txt

                    sshpass -p $PASS scp -o StrictHostKeyChecking=no test.txt $USER@pivote:/tmp/

                    sshpass -p $PASS ssh -o StrictHostKeyChecking=no $USER@pivote \\
                    "sshpass -p $PASS scp -o StrictHostKeyChecking=no /tmp/test.txt $USER@final:/tmp/"
                    """
                }
            }
        }
    }
}

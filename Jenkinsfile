pipeline {
    agent any

    triggers {
        pollSCM('* * * * *')
    }

    stages {

        stage('Test SSH to Pivote') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'ssh-lab-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {

                    sh '''
                    echo "Probando conexión a pivote..."

                    sshpass -p $PASS ssh -p 2222 -o StrictHostKeyChecking=no $USER@pivote \
                    "echo Conectado correctamente al servidor pivote"
                    '''
                }
            }
        }

        stage('Copy File to Pivote') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'ssh-lab-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {

                    sh '''
                    echo "archivo CI/CD desde Jenkins" > test.txt

                    echo "Copiando archivo al pivote..."
                    sshpass -p $PASS scp -P 2222 -o StrictHostKeyChecking=no test.txt $USER@pivote:/tmp/

                    echo "Verificando archivo en pivote..."
                    sshpass -p $PASS ssh -p 2222 -o StrictHostKeyChecking=no $USER@pivote \
                    "cat /tmp/test.txt"
                    '''
                }
            }
        }
    }
}

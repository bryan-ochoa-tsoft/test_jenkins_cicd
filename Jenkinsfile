pipeline {
    agent any

    triggers {
        pollSCM('* * * * *')
    }

    stages {

        stage('Test SSH via Bastion') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'ssh-lab-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {

                    sh '''
                    echo "Probando conexión vía pivote..."

                    sshpass -p $PASS ssh -p 2222 -o StrictHostKeyChecking=no $USER@pivote \
                    "sshpass -p $PASS ssh -p 2222 -o StrictHostKeyChecking=no $USER@final \
                    'echo Conectado correctamente al servidor final'"
                    '''
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

                    sh '''
                    echo "archivo CI/CD desde Jenkins" > test.txt

                    echo "Copiando archivo al pivote..."
                    sshpass -p $PASS scp -P 2222 -o StrictHostKeyChecking=no test.txt $USER@pivote:/tmp/

                    echo "Copiando archivo desde pivote al servidor final..."
                    sshpass -p $PASS ssh -p 2222 -o StrictHostKeyChecking=no $USER@pivote \
                    "sshpass -p $PASS scp -P 2222 -o StrictHostKeyChecking=no /tmp/test.txt $USER@final:/tmp/"

                    echo "Verificando archivo en servidor final..."
                    sshpass -p $PASS ssh -p 2222 -o StrictHostKeyChecking=no $USER@pivote \
                    "sshpass -p $PASS ssh -p 2222 -o StrictHostKeyChecking=no $USER@final \
                    'cat /tmp/test.txt'"
                    '''
                }
            }
        }
    }
}

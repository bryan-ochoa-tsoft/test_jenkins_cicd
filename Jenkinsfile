pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                sh '''
                echo "artifact $(date)" > artifact.txt
                '''
            }
        }

        stage('Deploy via Bastion') {
            steps {
                sshagent(['ssh-key-pivote']) {

                    sh '''
                    echo "Copiando archivo al pivote..."
                    scp -o StrictHostKeyChecking=no artifact.txt admin@pivote:/home/admin/

                    echo "Ejecutando despliegue desde pivote..."

                    ssh -o StrictHostKeyChecking=no admin@pivote "

                        echo '→ DEV'
                        scp /home/admin/artifact.txt admin@dev:/home/admin/
                        ssh admin@dev 'cat /home/admin/artifact.txt'

                        echo '→ PRD'
                        scp /home/admin/artifact.txt admin@prd:/home/admin/
                        ssh admin@prd 'cat /home/admin/artifact.txt'

                    "
                    '''
                }
            }
        }
    }
}

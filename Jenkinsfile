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

        stage('Deploy DEV via Bastion') {
            steps {
                sshagent(['ssh-key-pivote']) {

                    sh '''
                    echo "Deploy DEV..."

                    scp -o ProxyJump=admin@pivote -o StrictHostKeyChecking=no \
                    artifact.txt admin@dev:/tmp/

                    ssh -J admin@pivote admin@dev \
                    "cat /tmp/artifact.txt"
                    '''
                }
            }
        }
        
        stage('Deploy PRD via Bastion') {
            steps {
                sshagent(['ssh-key-pivote']) {

                    sh '''
                    echo "Deploy PRD..."

                    scp -o ProxyJump=admin@pivote -o StrictHostKeyChecking=no \
                    artifact.txt admin@prd:/tmp/

                    ssh -J admin@pivote admin@prd \
                    "cat /tmp/artifact.txt"
                    '''
                }
            }
        }
    }
}

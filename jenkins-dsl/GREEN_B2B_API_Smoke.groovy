pipelineJob('QA-Automation/MERITO/GREEN_B2B_API_Smoke') {
    parameters {
        stringParam('BRANCH', 'main', 'Repo branch')
    }
    definition {
        cps {
            script("""
                pipeline {
                    agent {
                        kubernetes {
                            yaml \"""
                            kind: Pod
                            spec:
                              containers:
                              - name: automation
                                image: registry.internal.techtank9.com/registry/analyst/automation/merito:\${params.BRANCH}
                                imagePullPolicy: Always
                                securityContext:
                                  privileged: true
                                  capabilities:
                                    add: ["SYS_ADMIN"]
                                env:
                                - name: DISPLAY
                                  value: ":0"
                                command:
                                - sleep
                                args:
                                - 99d
                                volumeMounts:
                                  - name: shm
                                    mountPath: /dev/shm
                              volumes:
                              - name: shm
                                hostPath:
                                    path: /dev/shm
                              imagePullSecrets:
                              - name: docker-credentials
                            \"""
                        }
                    }

                    stages {
                      stage ('Run') {
                        steps {
                          container(name: 'automation') {
                            sh '''cp /home/automation/chromedriver \$WORKSPACE'''
                            sh '''chmod +x \$WORKSPACE/chromedriver'''
                            sh '''sudo chown automation:automation /home/automation/'''
                            sh '''xvfb-run --server-args="-screen 0 1920x1080x24" java -cp '/home/automation/target/classes/:/home/automation/target/dependency/*' org.testng.TestNG /home/automation/src/main/api/smoke/green/PRO_B2B_API.xml'''
                          }
                        }
                      }
                    }

                    post { 
                      always { 
                        testNG(showFailedBuilds: true,
                               reportFilenamePattern: '**/testng-results.xml')
                        archiveArtifacts artifacts: '**',
                                        allowEmptyArchive: true,
                                        fingerprint: true
                      }
                    }
                }""".stripIndent()
            )
        }
    }
}

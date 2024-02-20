pipelineJob('QA-Automation/MERITO/FAIR999_Member_BLUE_Smoke') {
    parameters {
        stringParam('BRANCH', 'isa_enhace', 'Repo branch')
    }
    definition {
        cps {
            script("""
                import hudson.tasks.test.AbstractTestResultAction
import hudson.model.*

def summary
def failedTests
pipeline {
    agent {
        kubernetes {
            yaml \"""
                    kind: Pod
                    spec:
                            containers :
                    -name: automation
                    image: registry.internal.techtank9.com / registry / analyst / automation / merito : \$ { params.BRANCH }
                    imagePullPolicy: Always
                    securityContext:
                            privileged : true
                    capabilities:
                            add : ["SYS_ADMIN"]
                    env:
                            -name : DISPLAY
                    value: ":0"
                    command:
                            -sleep
                    args:
                            -99d
                    volumeMounts:
                            -name : shm
                    mountPath: /dev/ shm
                    volumes:
                            -name : shm
                    hostPath:
                            path : /dev/ shm
                    imagePullSecrets:
                            -name : docker - credentials
                    \"""
        }
    }

    stages {
      stage ('Run') {
        steps {
          slackSend channel: "#alert-merito-test-result",
                    message: "STARTED: Job \${env.JOB_NAME} [\${env.BUILD_NUMBER}] (\${env.BUILD_URL})",
                    tokenCredentialId: "merito/jenkins/slack_creds",
                    color: "normal"
          container(name: 'automation') {
            sh '''cp /home/automation/chromedriver \$WORKSPACE'''
            sh '''chmod +x \$WORKSPACE/chromedriver'''
            sh '''sudo chown automation:automation /home/automation/'''
            sh '''xvfb-run --server-args="-screen 0 1920x1080x24" java -cp '/home/automation/target/classes/:/home/automation/target/dependency/*' org.testng.TestNG /home/automation/src/main/suites/membersite/smoke/blue/fair999.xml'''
          }
        }
      }
    }

    post { 
      always { 
        testNG(showFailedBuilds: true,
               reportFilenamePattern: '**/testng-results.xml')
        logParser parsingRulesPath: '/var/jenkins_config/log-parser-rules.txt',
                  showGraphs: true,
                  useProjectRule: false,
                  projectRulePath: ""
        archiveArtifacts artifacts: '**',
                        allowEmptyArchive: true,
                        fingerprint: true
      script {
            summary = junit testResults: '**/junitreports/TEST-*.xml'
            AbstractTestResultAction testResultAction =  currentBuild.rawBuild.getAction(AbstractTestResultAction.class)
            failedTests = testResultAction.getResult().getFailedTests().collect { it.getTitle()+"\\n" }
            }
       }
      success {
        slackSend channel: "#alert-merito-test-result",
                  message: "SUCCESSFUL: Job \${env.JOB_NAME} [\${env.BUILD_NUMBER}] (<\${env.BUILD_URL}|Open>)\\n   Test Summary: \${summary.totalCount}\\nPassed: \${summary.passCount}, Failed: \${summary.failCount}, Skipped: \${summary.skipCount}\\nThe result is generated in TestRail. Please view accordingly test run for details\\nhttps://paltech.testrail.io/index.php?/runs/overview/1",
                  tokenCredentialId: "merito/jenkins/slack_creds",
                  color: "#00FF00"
      }
      failure {
        slackSend channel: "#alert-merito-test-result",
                  message: "FAILED: Job \${env.JOB_NAME} [\${env.BUILD_NUMBER}] (\${env.BUILD_URL})\\n   Test Summary: \${summary.totalCount}\\nPassed: \${summary.passCount}, Failed: \${summary.failCount}, Skipped: \${summary.skipCount}\\nFailed List:\\n\${failedTests}\\nThe result is generated in TestRail. Please view accordingly test run for details\\nhttps://paltech.testrail.io/index.php?/runs/overview/1",
                  tokenCredentialId: "merito/jenkins/slack_creds",
                  color: "#FF0000"
      }
    } 
}""".stripIndent()
            )
        }
    }
}

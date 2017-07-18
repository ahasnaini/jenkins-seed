folder('GoKubeDemo')

deliveryPipelineView('GoKubeDemo/Production') {
    pipelineInstances(5)
    columns(2)
    sorting(Sorting.TITLE)
    updateInterval(2)
    enableManualTriggers()
    showAvatars()
    showChangeLog()
    pipelines {
        component('Development', 'GoKubeDemo/PrepareDeploy')
    }
}

buildPipelineView('GoKubeDemo/Prod') {
    title('Prod')
    displayedBuilds(5)
    selectedJob('GoKubeDemo/PrepareDeploy')
    alwaysAllowManualTrigger()
    showPipelineParameters()
    refreshFrequency(2)
}

job('GoKubeDemo/Deploy') {
    label('master')
    scm {
        github('ahasnaini/gokubedemo', 'master')
    }
     properties {
        rebuild {
            autoRebuild()
        }
    }
    parameters {
     stringParam('IMAGE_TO_DEPLOY', 'NOIMAGEPASSED') 
   }
    wrappers {
        buildName('${IMAGE_TO_DEPLOY}')
    }
    steps {
        shell('echo $IMAGE_TO_DEPLOY')
        shell('echo "Deployed to production!"')
        shell('export PATH=$PATH:/var/jenkins_home/ && kubectl --context=prod.kubernetes.asadali.net apply -f deployment.yml')
        shell('export PATH=$PATH:/var/jenkins_home/ && kubectl --context=prod.kubernetes.asadali.net set image deployment/demo-app-deployment demo-app=asadali/gokubedemo:$IMAGE_TO_DEPLOY.Master')
    }
}

job('GoKubeDemo/PrepareDeploy') {
     parameters {
                        stringParam('IMAGE_TO_DEPLOY', 'NOIMAGEPASSED') 

            }
     wrappers {
        buildName('${IMAGE_TO_DEPLOY}')
    }
    publishers {
        buildPipelineTrigger('GoKubeDemo/Deploy') {
            parameters {

                        currentBuild()
            }
            }
        }
    }

pipelineJob('GoKubeDemo/Development') {
         definition {
        cpsScm {
            scm {
                     git('https://github.com/ahasnaini/gokubedemo.git','development',{node -> node / 'extensions' << '' })
            }
            scriptPath('Jenkinsfile')
        }
    }
  }
pipelineJob('GoKubeDemo/Master') {
         definition {
        cpsScm {
            scm {
              git('https://github.com/ahasnaini/gokubedemo.git','master',{node -> node / 'extensions' << '' })
            }
            scriptPath('Jenkinsfile')
        }
    }
  }

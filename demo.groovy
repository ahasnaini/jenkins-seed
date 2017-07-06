folder('GoKubeDemo')

deliveryPipelineView('GoKubeDemo/Production') {
    pipelineInstances(5)
    showAggregatedPipeline()
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
    parameters {
     stringParam('IMAGE_TO_DEPLOY', 'NOIMAGEPASSED') 
   }
    wrappers {
        buildName('${IMAGE_TO_DEPLOY}')
    }
    steps {
        shell('echo $IMAGE_TO_DEPLOY')
        shell('echo "Deployed to production!"')
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

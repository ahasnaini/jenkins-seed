folder('GoKubeDemo')

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

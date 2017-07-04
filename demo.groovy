folder('GoKubeDemo')
folder('GoKubeDemoMultiBranch')

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

multibranchPipelineJob('GoKubeDemoMultiBranch/Demo-Multibranch') {
        branchSources {
            github {
                repoOwner('ahasnaini')
                repository('gokubedemo')
                scanCredentialsId('9b803686-2f39-4a23-8f47-cb6428f69e1e')
                excludes('tags/*')
            }

            orphanedItemStrategy {
                discardOldItems {
                    numToKeep(5)
                }
            }

            triggers {
                // run once a day if not otherwise run
                periodic(1440)
            }
        }
    }

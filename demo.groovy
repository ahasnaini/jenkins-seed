folder('GoKubeDemo')

multibranchPipelineJob('GoKubeDemo/Demo-Multibranch') {
        branchSources {
            git {
                remote('https://github.com/ahasnaini/gokubedemo')
                credentialsId('github-ahasnaini')
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

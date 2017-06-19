multibranchPipelineJob('Demo-Multibranch') {
        branchSources {
            github {
                repoOwner('ahasnaini')
                repository('gokubedemo')
                scanCredentialsId('72fb065b-94d8-4642-a81e-4ef784922e88')
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

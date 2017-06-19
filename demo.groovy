multibranchPipelineJob('Demo-Multibranch') {
        branchSources {
            github {
                repoOwner('ahasnaini')
                repository('gokubedemo')
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

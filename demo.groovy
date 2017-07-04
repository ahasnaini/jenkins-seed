folder('GoKubeDemo')

job('GoKubeDemo/Deploy') {
    parameters {
     stringParam('IMAGE_TO_DEPLOY', 'NOIMAGEPASSED') 
   }
    steps {
        shell('echo $IMAGE_TO_DEPLOY')
        shell('echo "Deployed to production!"')
    }
}

multibranchPipelineJob('GoKubeDemo/Branches') {
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

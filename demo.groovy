folder('GoKubeDemo')

job('GoKubeDemo/Demo-Multibranch') {
         scm {
            github('ahasnaini/gokubedemo', 'development')
             }
            triggers {
                // run once a day if not otherwise run
                periodic(1440)
            }
  }

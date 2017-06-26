folder('GoKubeDemo')

job('GoKubeDemo/Demo-Multibranch') {
         scm {
            github('ahasnaini/gokubedemo', 'development')
             }
  }

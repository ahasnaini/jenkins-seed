folder('GoKubeDemo')

job('GoKubeDemo/Development') {
         scm {
            github('ahasnaini/gokubedemo', 'development')
             }
  }

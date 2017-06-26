folder('GoKubeDemo')

job('GoKubeDemo/Development') {
         scm {
            github('ahasnaini/gokubedemo', 'development')
             }
  }
job('GoKubeDemo/Master') {
         scm {
            github('ahasnaini/gokubedemo', 'master')
             }
  }

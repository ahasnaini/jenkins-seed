folder('GoKubeDemo')

pipelineJob('GoKubeDemo/Development') {
         scm {
            github('ahasnaini/gokubedemo', 'development')
             }
  }
pipelineJob('GoKubeDemo/Master') {
         scm {
            github('ahasnaini/gokubedemo', 'master')
             }
  }

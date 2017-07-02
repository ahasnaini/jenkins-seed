folder('GoKubeDemo')

pipelineJob('GoKubeDemo/Development') {
         definition {
        cpsScm {
            scm {
              git('https://github.com/ahasnaini/gokubedemo.git','development')
            }
            scriptPath('Jenkinsfile')
        }
    }
  }
pipelineJob('GoKubeDemo/Master') {
         definition {
        cpsScm {
            scm {
              git('https://github.com/ahasnaini/gokubedemo.git','master')
            }
            scriptPath('Jenkinsfile')
        }
    }
  }

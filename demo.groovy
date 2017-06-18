job('Demo') {
    scm {
        git('https://github.com/ahasnaini/gokubedemo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('ahasnaini@hotmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
}

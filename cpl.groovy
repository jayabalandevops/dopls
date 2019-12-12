folder("$BUName"){}
folder("$BUName/$ProdectName"){}
pipelineJob("$BUName/$ProductName/CICD_$AppName"){
    parameters{
        stringParam("AppRepo","$ApplicationRepo","Git URL")
        stringParam("NodeName","$NodeName","")
        stringParam("UnitTestTool","$UnitTestRun","")
        stringParam("AppName","$AppName","")
        activeChoiceParam('Branch'){
            description('Select the branch')
            filterable()
            choiceType('SINGLE_SELECT')
            groovyScript{
                script('["select","master","develop"]')
                fallbackScript('"fallback choice"')
            }
        }
    }
    definition{
        cps{
            def jobScript = readFileFromWorkspace('cit.groovy')
            script(jobScript)
            def approvals = org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval.get()
            approvals.approveScript(approvals.hash(jobScript,"groovy"))
        }
    }
}
node("$NodeName"){
    wrks = env.WORKSPACE
    stage("Prepare"){
        print("Preparing the workspace with pipeline scripts...")
        git(
            url: "git@github.com:jayabalandevops/dopls.git",
            branch: "master"
        )
        dir('config'){
            git(
                url: "git@github.com:jayabalandevops/doconfig.git",
                branch: "master"
            )
        }
        print("Prepared the workspace with pipeline scripts")
    }
    stage("AppClone"){
        print("Cloning the application into workspace...")
        load 'app/clone.groovy'
        print("Cloned the application from repo.")
    }
    stage("Build"){
        print("Building the application...")
        load 'app/build.groovy'
        print("Builded the application.")
    }

}
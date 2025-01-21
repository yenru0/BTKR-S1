java.sourceCompatibility = JavaVersion.VERSION_17

java.sourceSets["main"].java {
    srcDir("src/")
}

java.sourceSets["main"].resources {
    srcDir("../assets")
}

project.extra.set("mainClassName", "yenru0.yrkaier.btkr.s1.desktop.DesktopLauncher")
project.extra.set("assetDir", "../assets")

tasks.register<JavaExec>("run") {
    dependsOn("classes")
    mainClass.set(project.extra["mainClassName"] as String)
    classpath = java.sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
    workingDir = File(project.extra["assetDir"] as String)
    isIgnoreExitValue = true
    val args = jvmArgs!!.toMutableList()
    val os = System.getProperty("os.name").lowercase()
    if (os.contains("mac")) args.add("-XstartOnFirstThread")
    jvmArgs = args

}

tasks.register<JavaExec>("debug") {
    dependsOn("classes")
    mainClass.set(project.extra["mainClassName"] as String)
    classpath = java.sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
    workingDir = File(project.extra["assetDir"] as String)
    isIgnoreExitValue = true
    debug = true
}

tasks.register<Jar>("dist") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = project.extra["mainClassName"]
    }
    dependsOn(configurations.runtimeClasspath)
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory()) it else zipTree(it) })
}

tasks.named("dist") {
    dependsOn("classes")
}
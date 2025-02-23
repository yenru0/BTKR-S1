buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")

    }
    dependencies {

    }
}

allprojects {
    apply {
        plugin("idea")
    }
    version = "0.0.1"
    project.extra.set("appName", "BTKR-S1")
    project.extra.set("gdxVersion", "1.13.1")

    repositories {
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://oss.sonatype.org/content/repositories/releases/")
        maven("https://jitpack.io")
    }
}

project(":core") {
    apply(plugin = "java-library")
    dependencies {
        val gdxVersion: String by project.extra
        "api"("com.badlogicgames.gdx:gdx:$gdxVersion")
        "api"("com.badlogicgames.gdx:gdx-box2d:$gdxVersion")
        "api"("com.badlogicgames.gdx:gdx-freetype:$gdxVersion")
    }
}

project(":desktop") {
    apply(plugin = "java-library")
    dependencies {
        val gdxVersion: String by project.extra
        "implementation"(project(":core"))
        "api"("com.badlogicgames.gdx:gdx-backend-lwjgl3:$gdxVersion")
        "api"("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop")
        "implementation"("com.badlogicgames.gdx:gdx-box2d-platform:$gdxVersion:natives-desktop")
        "implementation"("com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-desktop")
    }
}
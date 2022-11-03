plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.tools.detekt)
    alias(libs.plugins.tools.klint)
    id("com.google.devtools.ksp") version "1.7.20-1.0.7"
    kotlin("android").version(libs.versions.kotlin.version).apply(false)
    kotlin("multiplatform").version(libs.versions.kotlin.version).apply(false)
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        debug.set(true)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        outputColorName.set("RED")
        filter {
            enableExperimentalRules.set(true)
            exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/") }
            // include { projectDir.toURI().relativize(it.file.toURI()).path.contains("/kotlin/") }
            include("**/kotlin/**")
        }
    }

    apply(plugin = "io.gitlab.arturbosch.detekt")
    detekt {
        parallel = true
        config = files("${project.rootDir}/config/detekt/detekt.yml")
    }

    /*apply(plugin = Plugins.kotlinxTestResource)

    tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {
        checkForGradleUpdate = true
        outputDir = "build/dependencyUpdates"
        reportfileName = "report"
    }*/
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


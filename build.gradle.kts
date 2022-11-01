plugins {
    //trick: for the same plugin versions in all sub-modules


    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false

    kotlin("android").version(libs.versions.kotlin.version).apply(false)
    kotlin("multiplatform").version(libs.versions.kotlin.version).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

pluginManagement {
    repositories {

        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        maven{
            url = uri("https://jitpack.io")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "GamePedia"
include(":androidApp")
include(":shared")
include(":domain")

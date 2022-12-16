plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.devtools.ksp")
}

kotlin {
    android()
    val iosTarget: (String, org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget.() -> Unit) -> org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }
    iosTarget("iOS") {}

    cocoapods {

        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../GamePediaiOS/Podfile")
        framework {
            baseName = "shared"
           export(libs.multiplatform.paging)
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {

                api(libs.koin.core)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.contentNegotiation)
                implementation(libs.ktor.client.json)
                implementation(libs.kotlinx.serialization.json)
                api(libs.logger.napier)
                api(libs.multiplatform.paging)
                // implementation(libs.cash.paging.common)
            }
        }

        sourceSets["iOSMain"].dependencies {
            implementation(libs.ktor.client.darwin)
        }

        val androidMain by getting {
            dependencies {
                api(libs.koin.android)
                implementation(libs.ktor.client.okhttp)
            }
        }
    }
}

android {
    namespace = "com.devmike.gamepedia"
    compileSdk = 33
    defaultConfig {
        minSdk = 29
        targetSdk = 33
    }
}

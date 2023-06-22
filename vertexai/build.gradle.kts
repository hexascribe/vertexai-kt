plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

kotlin {
    explicitApi()
    android()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "VertexAI"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.ktor.negotiation)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.core)
                implementation(libs.ktor.logging)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.okhttp)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.ios)
            }
        }
    }
}

android {
    namespace = "com.hexascribe.vertexai"
    compileSdk = libs.versions.config.compile.sdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.config.min.sdk.get().toInt()
    }
}
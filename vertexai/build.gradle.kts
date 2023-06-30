plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("io.gitlab.arturbosch.detekt")
    id("com.vanniktech.maven.publish")
    id("com.chromaticnoise.multiplatform-swiftpackage")
}

kotlin {
    explicitApi()
    android { publishLibraryVariants("release") }
    jvm()
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
        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.java)
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

mavenPublishing {
    pom {
        developers {
            developer {
                id.set("osugikoji")
                name.set("Koji Osugi")
                url.set("https://github.com/osugikoji")
            }
            developer {
                id.set("xyzwilliamxyz")
                name.set("William")
                url.set("https://github.com/xyzwilliamxyz")
            }
        }
    }
}

multiplatformSwiftPackage {
    packageName("VertexAI")
    swiftToolsVersion("5.3")
    outputDirectory(File(rootDir, "/"))
    targetPlatforms {
        iOS { v("13") }
    }
}

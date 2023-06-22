import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.compose")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.hexascribe.vertexai"
    compileSdk = libs.versions.config.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = "com.hexascribe.vertexai"
        minSdk = libs.versions.config.min.sdk.get().toInt()
        targetSdk = libs.versions.config.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val accessTokenKey = "ACCESS_TOKEN"
        val projectIdKey = "PROJECT_ID"
        val accessToken = System
            .getenv()
            .getOrDefault(accessTokenKey, gradleLocalProperties(rootDir).getProperty(accessTokenKey))
        val projectId = System
            .getenv()
            .getOrDefault(projectIdKey, gradleLocalProperties(rootDir).getProperty(projectIdKey))
        buildConfigField("String", "ACCESS_TOKEN", "\"$accessToken\"")
        buildConfigField("String", "PROJECT_ID", "\"$projectId\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":vertexai"))
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.preview)
    implementation(compose.uiTooling)
    implementation(libs.activity.compose)
    implementation(libs.app.compat)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
}

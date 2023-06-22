plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(libs.versions.gradlePlugin).apply(false)
    id("com.android.library").version(libs.versions.gradlePlugin).apply(false)
    id("org.jetbrains.compose").version(libs.versions.composePlugin).apply(false)
    id("com.google.dagger.hilt.android").version(libs.versions.hilt).apply(false)
    id("io.gitlab.arturbosch.detekt").version(libs.versions.detektPlugin).apply(false)
    kotlin("android").version(libs.versions.kotlinPlugin).apply(false)
    kotlin("multiplatform").version(libs.versions.kotlinPlugin).apply(false)
    kotlin("plugin.serialization").version(libs.versions.kotlinPlugin).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

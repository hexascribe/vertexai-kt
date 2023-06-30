plugins {
    id("org.springframework.boot")
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":vertexai"))
    implementation(platform(libs.spring.boot.dependencies))
    implementation(libs.spring.boot.web)
}

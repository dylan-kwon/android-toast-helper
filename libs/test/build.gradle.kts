import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("java-library")
    id("maven-publish")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    withSourcesJar()
    withJavadocJar()
}

kotlin {
    jvmToolchain(17)
}

publishing {
    val properties = loadProperties(
        rootProject.file("publish.properties").path
    )
    repositories {
        maven(properties["githubRepoUrl"].toString()) {
            credentials {
                username = properties["githubUserName"].toString()
                password = properties["githubToken"].toString()
            }
        }
    }
    publications {
        register<MavenPublication>(name) {
            groupId = properties["groupId"].toString()
            artifactId = "toast-helper-test"
            version = properties["versionName"].toString()

            afterEvaluate {
                from(components["java"])
            }
        }
    }
}

dependencies {
    implementation(projects.libs.core)
    implementation(libs.junit)
}
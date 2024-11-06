import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "dylan.kwon.toasthelper.android"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

publishing {
    val publishProperties = loadProperties(
        rootProject.file("publish.properties").path
    )
    val versionProperties = loadProperties(
        rootProject.file("version.properties").path
    )
    repositories {
        maven(publishProperties["githubRepoUrl"].toString()) {
            credentials {
                username = publishProperties["githubUserName"].toString()
                password = publishProperties["githubToken"].toString()
            }
        }
    }
    publications {
        register<MavenPublication>(name) {
            groupId = publishProperties["groupId"].toString()
            artifactId = "toast-helper-android"
            version = versionProperties["versionName"].toString()

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

dependencies {
    api(projects.libs.core)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.startup.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}

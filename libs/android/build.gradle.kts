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
            artifactId = "toast-helper-android"
            version = properties["versionName"].toString()

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

dependencies {
    api(projects.libs.core)
    implementation(libs.androidx.startup.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}

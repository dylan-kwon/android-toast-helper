import org.gradle.util.internal.GUtil.loadProperties

private val publishProperties = loadProperties(
    file("publish.properties")
)

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven(publishProperties["githubRepoUrl"].toString()) {
            credentials {
                username = publishProperties["githubUserName"].toString()
                password = publishProperties["githubToken"].toString()
            }
        }
    }
}

rootProject.name = "toast-helper"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":sample")
include(":libs:core")
include(":libs:android")

include(":libs:test")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Clean Android Architecture"
include(":app")
include(":domain")
include(":data-repository")
include(":data-remote")
include(":data-local")
include(":presentation-common")
include(":presentation-post")
include(":presentation-user")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven {
            url = uri("https://inexus.samentic.com/repository/samentic-android/")
            artifactUrls("https://inexus.samentic.com/repository/samentic-android/")
            credentials {
                username = "signal"
                password = "mR,A7,Na@s4&37@"
            }
        }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url = uri("https://inexus.samentic.com/repository/samentic-android/")
            artifactUrls("https://inexus.samentic.com/repository/samentic-android/")
            credentials {
                username = "signal"
                password = "mR,A7,Na@s4&37@"
            }
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "Note"
include(":app")
include(":feature-note:note-data")
include(":feature-note:note-domain")
include(":feature-note:note-ui")
include(":feature-note:note-models")
include(":base")
include(":di")

pluginManagement {
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
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
rootProject.name = "build-logic"

include(":convention")
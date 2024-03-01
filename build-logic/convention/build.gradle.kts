plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.gradleAndroid)
    compileOnly(libs.koltinGradlePlugin)
}
gradlePlugin {
    plugins {
        register("androidSourceFlavors") {
            id = "merat.android.source.flavors"
            implementationClass = "AndroidSourceFlavorsConventionPlugin"
        }
    }

    plugins {
        register("androidLibrary") {
            id = "merat.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }

    plugins {
        register("androidApplication") {
            id = "merat.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}
package util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import util.MeratVersions.versionCode
import util.MeratVersions.versionName

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = MeratVersions.compileSdk

        defaultConfig {
            minSdk = MeratVersions.minSdk
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            buildConfigField(
                type = "Integer",
                name = "VERSION_CODE",
                value = "$versionCode"
            )
            buildConfigField(
                type = "String",
                name = "VERSION_NAME",
                value = "\"$versionName\""
            )

            vectorDrawables {
                useSupportLibrary = true
            }

        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        dataBinding.enable = true
        buildFeatures.viewBinding = true
        buildFeatures.compose = true
        composeOptions.kotlinCompilerExtensionVersion = "1.4.3"

//        buildToolsVersion = MeratVersions.buildTools

        packaging {
            resources.excludes.add("META-INF/AL2.0")
            resources.excludes.add("META-INF/LGPL2.1")
            resources.excludes.add("META-INF/LICENSE.md")
            resources.excludes.add("META-INF/LICENSE-notice.md")
        }

    }


    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
            allWarningsAsErrors = false
        }
    }

}

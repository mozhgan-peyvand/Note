import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import util.MeratVersions
import util.configureKotlinAndroid

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("merat.android.source.flavors")
                apply("maven-publish")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.consumerProguardFiles("proguard-rules.pro")
                defaultConfig.targetSdk = MeratVersions.targetSdk
                defaultConfig.versionName = generateVersionName()
                defaultConfig.versionCode = generateVersionCode()
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
//                "implementation"(libs.findLibrary("kotlinStdlib").get())
//                "implementation"(libs.findLibrary("coreKtx").get())
//                "implementation"(libs.findLibrary("timber").get())
//                "implementation"(libs.findLibrary("mockitoCore").get())
//                "implementation"(libs.findLibrary("truth").get())
//                "implementation"(libs.findLibrary("junit").get())
//                "implementation"(libs.findLibrary("extJunit").get())
//                "implementation"(libs.findLibrary("espressoCore").get())
            }
        }
    }

    object Ext {
        const val versionBusiness = 2 // versionBusiness rage only between  1 to 9
        const val versionMajor = 6
        const val versionMinor = 1
        const val versionPatch = 0
        const val minimumSdkVersion = 21
        const val testVersion = 3
    }

    private fun generateVersionCode(): Int {
        return Ext.minimumSdkVersion * 10000000 + Ext.versionBusiness * 1000000 + Ext.versionMajor * 10000 + Ext.versionMinor * 100 + Ext.versionPatch
    }

    private fun generateVersionName(): String {
        return if (Ext.testVersion != 0)
            "${Ext.versionBusiness}.${Ext.versionMajor}.${Ext.versionMinor}.${Ext.versionPatch}.${Ext.testVersion}"
        else
            "${Ext.versionBusiness}.${Ext.versionMajor}.${Ext.versionMinor}.${Ext.versionPatch}"
    }
}
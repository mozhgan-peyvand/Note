import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import util.MeratVersions
import util.configureKotlinAndroid
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = MeratVersions.targetSdk
            }

            fun getDate(): String? {
                val date = SimpleDateFormat(
                    "yyyy_MM_dd_HH_mm_ss",
                    Locale.getDefault()
                ).format(Date()) // you can change it
                return date
            }


            extensions.configure<AppExtension> {
                applicationVariants.all {
                    val variant = this
                    variant.outputs
                        .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
                        .forEach { output ->
                            if (variant.flavorName == "prod" || variant.flavorName == "stage") {
                                val outputFileName =
                                    "Merat-v${variant.versionName}(${variant.versionCode})-${variant.flavorName}-${variant.buildType.name}.apk"
                                output.outputFileName = outputFileName
                            } else {
                                val outputFileName =
                                    "Merat-build${getDate()}-${variant.flavorName}-${variant.buildType.name}.apk"
                                output.outputFileName = outputFileName
                            }
                        }
                }
            }
        }
    }
}
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import util.MeratFlavor
import util.configureFlavor

private val sourceFlavors = listOf(
    MeratFlavor(
        name = "prod",
        dimension = "mode"
    ),
    MeratFlavor(
        name = "stage",
        dimension = "mode"
    )
)

class AndroidSourceFlavorsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<BaseExtension> {
                flavorDimensionList += "mode"
                configureFlavor(sourceFlavors, target = target)
            }
        }
    }
}
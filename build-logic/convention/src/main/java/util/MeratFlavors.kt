package util

import com.android.build.api.dsl.ProductFlavor
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.provideDelegate

data class MeratFlavor(
    val name: String,
    val dimension: String,
    val buildConfigs: List<BuildConfigField> = listOf()
)

data class BuildConfigField(
    val type: String,
    val name: String,
    val value: String
)

fun BaseExtension.configureFlavor(
    flavors: List<MeratFlavor>,
    flavorConfigurationBlock: ProductFlavor.(flavor: MeratFlavor) -> Unit = {},
    target: Project
) {
    productFlavors {
        flavors.forEach {
            create(it.name) {
                flavorConfigurationBlock(it)
                dimension = it.dimension
//                buildConfigField(
//                    "String",
//                    "usePublishDependencyInGradle",
//                    "2.6.1.0"
//                )

                when (it.name) {
                    "stage" -> {
                        resValue("string", "API_URL", "\"https://test.imerat.ir\"")
                    }

                    "prod" -> {
                        resValue("string", "API_URL", "\"https://user.imerat.ir\"")
                    }
                }
            }
        }
    }
}

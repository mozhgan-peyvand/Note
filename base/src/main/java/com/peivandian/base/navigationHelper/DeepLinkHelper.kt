package ir.part.sdk.ara.common.ui.view.navigationHelper

import android.net.Uri
import androidx.core.net.toUri

/**
 * This class follows Builder pattern and is responsible for building an deep link
 * using navigation component through the project.
 *
 * @property module, The name of the module and must be the exact name. for example : ui-files
 * @property screen, The simple name of the screen. for example : SubmitRequestScreen
 * @property args, It's an array of any thing for including inside of uri.
 *
 * Below, is a formula of uri that should be used inside of the graph
 * for defining an deeplink.
 *
 * app.ara://module name (all lower case)/screen name (Pascal convention)/{argument1}/{argument2}
 *
 * The order of arguments passed through the [args] should be the exact order
 * inside of deeplink's definition in navigation.xml
 *
 */
class DeepLinkHelper(
    val module: String,
    val screen: String,
    private val args: Array<Any>? = null
) {
    private constructor(
        builder: Builder
    ) : this(builder.module, builder.screen, builder.args)

    class Builder {
        var module: String = ""
            private set

        var screen: String = ""
            private set

        var args: Array<Any>? = null
            private set

        fun module(module: String) = apply { this.module = module }

        fun screen(screen: String) = apply { this.screen = screen }

        fun args(args: Array<Any>) = apply { this.args = args }

        fun build() = DeepLinkHelper(this)
    }

    val uri: Uri get() {
        var result = "app.ara://$module/$screen"

        args?.forEach {
            result += "/{$it}"
        }
        return result.toUri()
    }
}
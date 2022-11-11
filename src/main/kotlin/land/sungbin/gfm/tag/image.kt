/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag

import land.sungbin.gfm.MarkdownTag

class Image(
    private val altText: String,
    private var url: String = "",
) : MarkdownTag() {

    override fun content(): String = "![$altText]($url)"

    operator fun String.unaryPlus() {
        url = this
    }
}

fun image(altText: String, block: Image.() -> Unit): MarkdownTag {
    return Image(altText).apply(block)
}

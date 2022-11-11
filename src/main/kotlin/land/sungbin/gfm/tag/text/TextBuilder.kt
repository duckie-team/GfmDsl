/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag.text

import land.sungbin.gfm.MarkdownTag

class TextBuilder : MarkdownTag() {

    private val sequence = mutableListOf<TextTag>()

    operator fun TextTag.unaryPlus() = sequence.add(this)

    override fun content(): String = sequence.joinToString(" ")
}

fun text(block: TextBuilder.() -> Unit): TextBuilder {
    return TextBuilder().apply(block)
}

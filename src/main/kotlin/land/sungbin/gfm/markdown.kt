/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm

class Markdown internal constructor() {
    private val content = mutableListOf<MarkdownTag>()

    operator fun MarkdownTag.unaryPlus() {
        content.add(
            element = this,
        )
    }

    override fun toString() = content.joinToString(
        separator = System.lineSeparator()
    ) { tag ->
        tag.content()
    }
}

fun markdown(@GfmDsl block: Markdown.() -> Unit): Markdown {
    return Markdown().apply(block)
}

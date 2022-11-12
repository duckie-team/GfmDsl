/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm

import land.sungbin.gfm.tag.text

public class Markdown private constructor() {
    private val contents = mutableListOf<MarkdownTag>()

    public operator fun MarkdownTag.unaryPlus() {
        contents.add(this)
    }

    public operator fun String.unaryPlus() {
        contents.add(text(this))
    }

    public override fun toString(): String = contents.joinToString(
        separator = "\n",
    ) { tag ->
        "${tag.content()}\n"
    }

    public companion object {
        public fun markdown(@GfmDsl block: Markdown.() -> Unit): Markdown {
            return Markdown().apply(block)
        }
    }
}

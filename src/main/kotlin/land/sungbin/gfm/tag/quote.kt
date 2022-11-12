/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag

import land.sungbin.gfm.GfmDsl
import land.sungbin.gfm.MarkdownTag

public class Quote @PublishedApi internal constructor() : MarkdownTag() {
    private val contents = mutableListOf<MarkdownTag>()

    public operator fun MarkdownTag.unaryPlus() {
        contents.add(this)
    }

    public operator fun String.unaryPlus() {
        contents.add(text(this))
    }

    override fun content(): String {
        return contents.joinToString(
            separator = "${LineBreak}\n",
        ) { tag ->
            "> $tag"
        }
    }
}

public inline fun quote(@GfmDsl builder: Quote.() -> Unit): MarkdownTag {
    return Quote().apply(builder)
}
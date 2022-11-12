/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag

import land.sungbin.gfm.GfmDsl
import land.sungbin.gfm.MarkdownTag


public class List @PublishedApi internal constructor() : MarkdownTag() {
    private val contents = mutableListOf<MarkdownTag>()
    private var useNumbers = false

    private fun checkUseNumbersDuplicateSetting() {
        if (useNumbers) {
            throw IllegalStateException("numberStyle is already set")
        }
    }

    public fun numberStyle() {
        checkUseNumbersDuplicateSetting()
        useNumbers = true
    }

    public operator fun MarkdownTag.unaryPlus() {
        contents.add(this)
    }

    public operator fun String.unaryPlus() {
        contents.add(text(text = this))
    }

    public override fun content(): String {
        return buildString {
            contents.forEachIndexed { index, content ->
                if (content is List) {
                    throw UnsupportedOperationException("Currently, nested list is not supported")
                }
                if (useNumbers) {
                    append("${index + 1}. ")
                } else {
                    append("- ")
                }
                append(
                    content.toString()
                        .split("\n")
                        .mapIndexed { codeIndex, code ->
                            if (codeIndex == 0) {
                                code
                            } else {
                                "   $code"
                            }
                        }
                        .joinToString("\n")
                )
                append("\n")
            }
        }
    }
}

public inline fun list(@GfmDsl builder: List.() -> Unit): List {
    return List().apply(builder)
}
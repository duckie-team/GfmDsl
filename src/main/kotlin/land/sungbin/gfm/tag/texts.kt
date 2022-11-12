/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag

import land.sungbin.gfm.GfmDsl
import land.sungbin.gfm.MarkdownTag

public class Text @PublishedApi internal constructor(
    private var text: String = "",
) : MarkdownTag() {
    private var bold = false
    private var italic = false
    private var underline = false
    private var strikethrough = false
    private var link = ""

    private fun ensureTextInit() {
        if (text.isEmpty()) {
            throw IllegalStateException("Text is empty!")
        }
    }

    private fun checkBoldDuplicateSetting() {
        if (bold) {
            throw IllegalStateException("Bold is already set to true.")
        }
    }

    private fun checkItalicDuplicateSetting() {
        if (italic) {
            throw IllegalStateException("Italic is already set to true.")
        }
    }

    private fun checkUnderlineDuplicateSetting() {
        if (underline) {
            throw IllegalStateException("Underline is already set to true.")
        }
    }

    private fun checkStrikethroughDuplicateSetting() {
        if (strikethrough) {
            throw IllegalStateException("Strikethrough is already set to true.")
        }
    }

    private fun checkLinkDuplicateSetting() {
        if (link.isNotEmpty()) {
            throw IllegalStateException("Link is already set to $link.")
        }
    }

    private fun checkTextDuplicateSetting() {
        if (text.isNotEmpty()) {
            throw IllegalStateException("Text is already set to $text.")
        }
    }

    public fun bold() {
        checkBoldDuplicateSetting()
        bold = true
    }

    public fun italic() {
        checkItalicDuplicateSetting()
        italic = true
    }

    public fun underline() {
        checkUnderlineDuplicateSetting()
        underline = true
    }

    public fun strikethrough() {
        checkStrikethroughDuplicateSetting()
        strikethrough = true
    }

    public fun link(src: String) {
        checkLinkDuplicateSetting()
        link = src
    }

    public operator fun String.unaryPlus() {
        checkTextDuplicateSetting()
        text = this
    }

    public override fun content(): String {
        ensureTextInit()

        val startTags = mutableListOf<String>()
        val endTags = mutableListOf<String>()

        if (bold) {
            startTags.add("**")
            endTags.add("**")
        }
        if (italic) {
            startTags.add("*")
            endTags.add("*")
        }
        if (strikethrough) {
            startTags.add("~~")
            endTags.add("~~")
        }
        if (underline) {
            startTags.add("<ins>")
            endTags.add("</ins>")
        }

        val text = startTags.joinToString(separator = "")
            .plus(text)
            .plus(endTags.reversed().joinToString(separator = ""))

        return if (link.isNotEmpty()) {
            "[$text]($link)"
        } else {
            text
        }
    }
}

public fun text(text: String): Text {
    return Text(text = text)
}

public inline fun text(@GfmDsl builder: Text.() -> Unit): Text {
    return Text().apply(builder)
}

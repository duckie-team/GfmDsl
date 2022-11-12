/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag

import land.sungbin.gfm.GfmDsl
import land.sungbin.gfm.MarkdownTag

public class Code @PublishedApi internal constructor(
    private var code: String = "",
) : MarkdownTag() {
    private var language: String = ""

    private fun ensureCodeInit() {
        if (code.isEmpty()) {
            throw IllegalStateException("Code is empty!")
        }
    }

    private fun checkCodeDuplicateSetting() {
        if (code.isNotEmpty()) {
            throw IllegalStateException("Code is already set to $code.")
        }
    }

    private fun checkLanguageDuplicateSetting() {
        if (language.isNotEmpty()) {
            throw IllegalStateException("Language is already set to $language.")
        }
    }

    public fun language(language: String) {
        checkLanguageDuplicateSetting()
        this.language = language
    }

    public operator fun String.unaryPlus() {
        checkCodeDuplicateSetting()
        code = this
    }

    override fun content(): String {
        ensureCodeInit()
        return "```$language\n$code\n```"
    }
}

public fun code(text: String): Text = text(text = "`$text`")

public inline fun code(
    @GfmDsl builder: Code.() -> Unit,
): Code {
    return Code().apply(builder)
}
/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag.text

import land.sungbin.gfm.tag.MarkdownTag

public class PlainText internal constructor(
    private val text: String,
) : MarkdownTag() {
    public override fun content(): String = text
}


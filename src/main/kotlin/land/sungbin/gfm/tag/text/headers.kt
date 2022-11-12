/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag.text

import land.sungbin.gfm.tag.MarkdownTag

public open class HeaderText internal constructor(
    private val text: String,
    private val level: Int,
) : MarkdownTag() {
    public override fun content(): String = "${"#".repeat(level)} $text"
}

public class H1(text: String) : HeaderText(
    text = text,
    level = 1,
)

public class H2(text: String) : HeaderText(
    text = text,
    level = 2,
)

public class H3(text: String) : HeaderText(
    text = text,
    level = 3,
)

public class H4(text: String) : HeaderText(
    text = text,
    level = 4,
)

public class H5(text: String) : HeaderText(
    text = text,
    level = 5,
)

public class H6(text: String) : HeaderText(
    text = text,
    level = 6,
)

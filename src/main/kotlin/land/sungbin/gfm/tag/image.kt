/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag

public class Image private constructor(
    private val alt: String,
    private val src: String,
    private val width: String?,
) : MarkdownTag() {
    public override fun content(): String {
        return if (width == null) {
            "![$alt]($src)"
        } else {
            "<img src=\"$src\" alt=\"$alt\" width=\"$width\" />"
        }
    }

    public companion object {
        public fun image(
            alt: String = "",
            src: String,
            width: String? = null,
        ): MarkdownTag = Image(
            alt = alt,
            src = src,
            width = width,
        )
    }
}

/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag

import land.sungbin.gfm.MarkdownTag

object NewLine : MarkdownTag() {
    override fun content() = "  "
}

object Br : MarkdownTag() {
    override fun content() = "<br/>"
}
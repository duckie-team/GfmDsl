/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag

public object LineBreak : MarkdownTag() {
    public override fun content(): String = "  "
}

public object NewLine : MarkdownTag() {
    public override fun content(): String = "<br/>"
}
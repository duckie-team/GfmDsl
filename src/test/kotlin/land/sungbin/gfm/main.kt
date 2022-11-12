/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import land.sungbin.gfm.Markdown.Companion.markdown
import land.sungbin.gfm.tag.Image.Companion.image
import land.sungbin.gfm.tag.LineBreak
import land.sungbin.gfm.tag.NewLine
import land.sungbin.gfm.tag.Table.Body.Companion.body
import land.sungbin.gfm.tag.Table.Companion.table
import land.sungbin.gfm.tag.Table.Header.Companion.header
import land.sungbin.gfm.tag.Underline
import land.sungbin.gfm.tag.text.H1
import land.sungbin.gfm.tag.text.H2
import land.sungbin.gfm.tag.text.H3
import land.sungbin.gfm.tag.text.H4
import land.sungbin.gfm.tag.text.H5
import land.sungbin.gfm.tag.text.H6

public fun main() {
    println(
        markdown {
            headersTest()
            imagesTest()
            underlineTest()
            linesTest()
            tablesTest()
        }.also { markdown ->
            copyToClipboard(markdown.toString())
        }
    )
}

private fun copyToClipboard(text: String) {
    val selection = StringSelection(text)
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(selection, selection)
}

private fun Markdown.headersTest() {
    +H2(text = "Headers")
    +H1(text = "Hello, world!")
    +H2(text = "Hello, world!")
    +H3(text = "Hello, world!")
    +H4(text = "Hello, world!")
    +H5(text = "Hello, world!")
    +H6(text = "Hello, world!")
}

private fun Markdown.imagesTest() {
    +H2(text = "Images")
    +image(
        alt = "GitHub - 50%",
        src = "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png",
        width = "10%",
    )
    +image(
        alt = "GitHub - 100%",
        src = "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png",
    )
}

private fun Markdown.underlineTest() {
    +H2(text = "Underline")
    +Underline
}

private fun Markdown.linesTest() {
    +H2(text = "lines")
    +NewLine
    +LineBreak
}

private fun Markdown.tablesTest() {
    +H2(text = "Tables")
    +H4(text = "Center")
    +table {
        centerAlign()
        +header {
            +"Header 1"
            +"Header 2"
            +"Header 3"
        }
        +body {
            +"Body 1"
            +"Body 2"
            +"Body 3"
        }
        +body {
            +"Body 4"
            +"Body 5"
            +"Body 6"
        }
    }
    +H4(text = "Left")
    +table {
        leftAlign()
        +header {
            +"Header 1"
            +"Header 2"
            +"Header 3"
        }
        +body {
            +"Body 1"
            +"Body 2"
            +"Body 3"
        }
        +body {
            +"Body 4"
            +"Body 5"
            +"Body 6"
        }
    }
    +H4(text = "Right")
    +table {
        rightAlign()
        +header {
            +"Header 1"
            +"Header 2"
            +"Header 3"
        }
        +body {
            +"Body 1"
            +"Body 2"
            +"Body 3"
        }
        +body {
            +"Body 4"
            +"Body 5"
            +"Body 6"
        }
    }
}
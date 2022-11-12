/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

@file:Suppress("SpellCheckingInspection")

package land.sungbin.gfm

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import land.sungbin.gfm.tag.H1
import land.sungbin.gfm.tag.H2
import land.sungbin.gfm.tag.H3
import land.sungbin.gfm.tag.H4
import land.sungbin.gfm.tag.H5
import land.sungbin.gfm.tag.H6
import land.sungbin.gfm.tag.LineBreak
import land.sungbin.gfm.tag.NewLine
import land.sungbin.gfm.tag.Underline
import land.sungbin.gfm.tag.code
import land.sungbin.gfm.tag.html
import land.sungbin.gfm.tag.image
import land.sungbin.gfm.tag.list
import land.sungbin.gfm.tag.quote
import land.sungbin.gfm.tag.table
import land.sungbin.gfm.tag.text

public fun main() {
    println(
        markdown {
            headers()
            images()
            underline()
            lines()
            tables()
            texts()
            quotes()
            codes()
            htmls()
            lists()
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

private fun Markdown.headers() {
    +H2(text = "Headers")
    +H1(text = "Header 1")
    +H2(text = "Header 2")
    +H3(text = "Header 3")
    +H4(text = "Header 4")
    +H5(text = "Header 5")
    +H6(text = "Header 6")
}

private fun Markdown.images() {
    +H2(text = "Images")
    +image(
        alt = "GitHub - 10%",
        src = "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png",
        width = "10%",
    )
    +image(
        alt = "GitHub - 100%",
        src = "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png",
    )
}

private fun Markdown.underline() {
    +H2(text = "Underline")
    +Underline
}

private fun Markdown.lines() {
    +H2(text = "Lines")
    +LineBreak
    +NewLine
}

private fun Markdown.tables() {
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
    +H4(text = "Default")
    +table {
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

private fun Markdown.texts() {
    +H2(text = "Texts")
    +"Plain Text"
    +text {
        bold()
        italic()
        underline()
        strikethrough()
        link(src = "https://github.com/duckie-team/GfmDsl")
        +"Bold, Italic, Underline, Strikethrough, Link"
    }
}

private fun Markdown.quotes() {
    +H2(text = "Quote")
    +quote {
        +"Quote"
        +text {
            bold()
            +"Image Quote"
        }
        +image(
            alt = "GitHub - 10%",
            src = "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png",
            width = "10%",
        )
        +quote {
            +text {
                bold()
                +"Nested Quote"
            }
            +quote {
                +"Double Nested Quote"
                +quote {
                    +quote {
                        +"Triple Nested Quote"
                    }
                }
            }
        }
    }
}

private fun Markdown.codes() {
    +H2(text = "Codes")
    +code(text = "Plain Code")
    +code {
        +"""
            |fun main() {
            |    println("Hello, Text!")
            |}
        """.trimMargin()
    }
    +code {
        language(language = "kotlin")
        +"""
            |fun main() {
            |    println("Hello, Kotlin!")
            |}
        """.trimMargin()
    }
}

private fun Markdown.htmls() {
    +H2(text = "HTML")
    +html(
        code = """
            |<p align="center">
            |  <a href="http://nestjs.com/" target="blank"><img src="https://nestjs.com/img/logo-small.svg" width="200" alt="Nest Logo" /></a>
            |</p>
        """.trimMargin()
    )
}

private fun Markdown.lists() {
    +H2(text = "Lists")
    +H4(text = "Bullet style")
    +list {
        +"One"
        +code(text = "Two")
        +image(
            alt = "GitHub - 10%",
            src = "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png",
            width = "10%",
        )
    }
    +H4(text = "Number style")
    +list {
        numberStyle()
        +"One"
        +code {
            language(language = "kotlin")
            +"""
                |fun main() {
                |    println("Hello, Kotlin!")
                |}
            """.trimMargin()
        }
        +text {
            bold()
            +"Bolded three"
        }
    }
}
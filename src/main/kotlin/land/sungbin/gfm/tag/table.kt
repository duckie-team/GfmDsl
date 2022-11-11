/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag

import land.sungbin.gfm.MarkdownTag

class Table : MarkdownTag() {
    class TableHeader : MarkdownTag() {
        private val headers = mutableListOf<String>()
        val count get() = headers.size

        operator fun String.unaryPlus() = headers.add(this)

        override fun content(): String = "|" + headers.joinToString("|") + "|"
    }

    class TableBody : MarkdownTag() {

        private val content = mutableListOf<MarkdownTag>()

        operator fun MarkdownTag.unaryPlus() = content.add(this)

        override fun content(): String = "|" + content.joinToString("|") + "|"
    }

    private var tableHeader: TableHeader? = null
    private var tableBody: TableBody? = null

    operator fun TableHeader.unaryPlus() {
        tableHeader = this
    }

    operator fun TableBody.unaryPlus() {
        tableBody = this
    }

    override fun content(): String {
        return if (tableHeader != null && tableBody != null) {
            StringBuilder().apply {
                append(tableHeader.toString())
                append(System.lineSeparator())

                val divider = "|" + "-|".repeat(tableHeader!!.count)
                append(divider)
                append(System.lineSeparator())

                append(tableBody.toString())
            }.toString()
        } else throw IllegalStateException("Table header or body cannot be null!")
    }
}

fun table(block: Table.() -> Unit): Table {
    return Table().apply(block)
}

fun header(block: Table.TableHeader.() -> Unit): Table.TableHeader {
    return Table.TableHeader().apply(block)
}

fun body(block: Table.TableBody.() -> Unit): Table.TableBody {
    return Table.TableBody().apply(block)
}

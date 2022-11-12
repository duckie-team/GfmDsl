/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

@file:Suppress("UnusedReceiverParameter", "NOTHING_TO_INLINE")

package land.sungbin.gfm.tag

import land.sungbin.gfm.GfmDsl
import land.sungbin.gfm.tag.text.PlainText

public class Table private constructor() : MarkdownTag() {
    private enum class Align {
        Left, Right, Center, Default;
    }

    public class Header private constructor() : MarkdownTag() {
        internal val contents = mutableListOf<String>()

        public operator fun String.unaryPlus() {
            contents.add(this)
        }

        public override fun content(): String = "|" + contents.joinToString("|") + "|"

        public companion object {
            public fun Table.header(@GfmDsl block: Header.() -> Unit): Header {
                return Header().apply(block)
            }
        }
    }

    public class Body private constructor() : MarkdownTag() {
        internal val contents = mutableListOf<MarkdownTag>()

        public operator fun MarkdownTag.unaryPlus() {
            contents.add(this)
        }

        public operator fun String.unaryPlus() {
            contents.add(PlainText(text = this))
        }

        public override fun content(): String = "|" + contents.joinToString("|") + "|"

        public companion object {
            public fun Table.body(@GfmDsl block: Body.() -> Unit): Body {
                return Body().apply(block)
            }
        }
    }

    private lateinit var header: Header
    private val bodies = mutableListOf<Body>()
    private var align = Align.Default

    private fun ensureTableInit() {
        if (!::header.isInitialized || bodies.isEmpty()) {
            throw IllegalStateException("Table must be initialized with header and body")
        }
    }

    private fun checkHeaderDuplicateSetting() {
        if (::header.isInitialized) {
            throw IllegalStateException("Header is already initialized")
        }
    }

    private fun checkAlignDuplicateSetting() {
        if (align != Align.Default) {
            throw IllegalStateException("Table align is already set")
        }
    }

    private fun checkHeaderAndBodiesContentSizeSame() {
        val headerSize = header.contents.size
        val bodySize = bodies.map { body ->
            body.contents.size
        }.toSet().singleOrNull()
        if (headerSize != bodySize) {
            throw IllegalStateException("Header and bodies content size must be same")
        }
    }

    public operator fun Header.unaryPlus() {
        checkHeaderDuplicateSetting()
        header = this
    }

    public operator fun Body.unaryPlus() {
        bodies.add(this)
    }

    public fun leftAlign() {
        checkAlignDuplicateSetting()
        align = Align.Left
    }

    public fun rightAlign() {
        checkAlignDuplicateSetting()
        align = Align.Right
    }

    public fun centerAlign() {
        checkAlignDuplicateSetting()
        align = Align.Center
    }

    private inline fun divider(): String {
        return "|" + (when (align) {
            Align.Left -> ":---|"
            Align.Right -> "---:|"
            Align.Center -> ":---:|"
            Align.Default -> "---|"
        }).repeat(header.contents.size)
    }

    public override fun content(): String {
        ensureTableInit()
        checkHeaderAndBodiesContentSizeSame()
        return buildString {
            append(header)
            appendLine()

            append(divider())
            appendLine()

            append(bodies.joinToString("\n"))
        }
    }

    public companion object {
        public fun table(@GfmDsl block: Table.() -> Unit): Table {
            return Table().apply(block)
        }
    }
}


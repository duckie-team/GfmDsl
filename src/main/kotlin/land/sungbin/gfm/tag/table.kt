/*
 * Designed and developed by Martin Macheiner and Ji Sungbin, 2022
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/GfmDsl/blob/master/LICENSE
 */

package land.sungbin.gfm.tag

import land.sungbin.gfm.GfmDsl
import land.sungbin.gfm.MarkdownTag

public class Table @PublishedApi internal constructor() : MarkdownTag() {
    private enum class Align {
        Left, Right, Center, Default;
    }

    public class Header @PublishedApi internal constructor() : MarkdownTag() {
        internal val contents = mutableListOf<String>()

        public operator fun String.unaryPlus() {
            contents.add(this)
        }

        public override fun content(): String = "|" + contents.joinToString("|") + "|"
    }

    public class Body @PublishedApi internal constructor() : MarkdownTag() {
        internal val contents = mutableListOf<MarkdownTag>()

        public operator fun MarkdownTag.unaryPlus() {
            contents.add(this)
        }

        public operator fun String.unaryPlus() {
            contents.add(text(this))
        }

        public override fun content(): String = "|" + contents.joinToString("|") + "|"
    }

    private lateinit var _header: Header
    private val bodies = mutableListOf<Body>()
    private var align = Align.Default

    private fun ensureTableInit() {
        if (!::_header.isInitialized || bodies.isEmpty()) {
            throw IllegalStateException("Table must be initialized with header and body")
        }
    }

    private fun checkHeaderDuplicateSetting() {
        if (::_header.isInitialized) {
            throw IllegalStateException("Header is already initialized")
        }
    }

    private fun checkAlignDuplicateSetting() {
        if (align != Align.Default) {
            throw IllegalStateException("Table align is already set")
        }
    }

    private fun checkHeaderAndBodiesContentSizeSame() {
        val headerSize = _header.contents.size
        val bodySize = bodies.map { body ->
            body.contents.size
        }.toSet().singleOrNull()
        if (headerSize != bodySize) {
            throw IllegalStateException("Header and bodies content size must be same")
        }
    }

    public operator fun Header.unaryPlus() {
        checkHeaderDuplicateSetting()
        _header = this
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

    private fun divider(): String {
        return "|" + (when (align) {
            Align.Left -> ":---|"
            Align.Right -> "---:|"
            Align.Center -> ":---:|"
            Align.Default -> "---|"
        }).repeat(_header.contents.size)
    }

    public override fun content(): String {
        ensureTableInit()
        checkHeaderAndBodiesContentSizeSame()
        return buildString {
            append(_header)
            appendLine()

            append(divider())
            appendLine()

            append(bodies.joinToString("\n"))
        }
    }

    public inline fun header(@GfmDsl block: Header.() -> Unit): Header {
        return Header().apply(block)
    }

    public inline fun body(@GfmDsl block: Body.() -> Unit): Body {
        return Body().apply(block)
    }
}

public inline fun table(@GfmDsl block: Table.() -> Unit): MarkdownTag {
    return Table().apply(block)
}

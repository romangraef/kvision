/*
 * Copyright (c) 2017-present Robert Jaros
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package pl.treksoft.kvision.core

import pl.treksoft.kvision.utils.Cache
import pl.treksoft.kvision.utils.asString
import kotlin.reflect.KProperty

/**
 * Base class for components supporting CSS styling.
 */
@Suppress("LargeClass")
abstract class StyledComponent {
    private val propertyValues: MutableMap<String, Any?> = mutableMapOf()
    internal val customStyles: MutableMap<String, String> = mutableMapOf()

    /**
     * Width of the current component.
     */
    open var width: CssSize? by refreshOnUpdate()

    /**
     * Minimal width of the current component.
     */
    open var minWidth: CssSize? by refreshOnUpdate()

    /**
     * Maximal width of the current component.
     */
    open var maxWidth: CssSize? by refreshOnUpdate()

    /**
     * Height of the current component.
     */
    open var height: CssSize? by refreshOnUpdate()

    /**
     * Minimal height of the current component.
     */
    open var minHeight: CssSize? by refreshOnUpdate()

    /**
     * Maximal height of the current component.
     */
    open var maxHeight: CssSize? by refreshOnUpdate()

    /**
     * CSS display of the current component.
     */
    open var display: Display? by refreshOnUpdate()

    /**
     * CSS position of the current component.
     */
    open var position: Position? by refreshOnUpdate()

    /**
     * Top edge of the current component.
     */
    open var top: CssSize? by refreshOnUpdate()

    /**
     * Left edge of the current component.
     */
    open var left: CssSize? by refreshOnUpdate()

    /**
     * Right edge of the current component.
     */
    open var right: CssSize? by refreshOnUpdate()

    /**
     * Bottom edge of the current component.
     */
    open var bottom: CssSize? by refreshOnUpdate()

    /**
     * Z-index of the current component.
     */
    open var zIndex: Int? by refreshOnUpdate()

    /**
     * CSS overflow of the current component.
     */
    open var overflow: Overflow? by refreshOnUpdate()

    /**
     * CSS overflow-wrap of the current component.
     */
    open var overflowWrap: OverflowWrap? by refreshOnUpdate()

    /**
     * CSS resize of the current component.
     */
    open var resize: Resize? by refreshOnUpdate()

    /**
     * Border of the current component.
     */
    open var border: Border? by refreshOnUpdate()

    /**
     * Top border of the current component.
     */
    open var borderTop: Border? by refreshOnUpdate()

    /**
     * Right border of the current component.
     */
    open var borderRight: Border? by refreshOnUpdate()

    /**
     * Bottom border of the current component.
     */
    open var borderBottom: Border? by refreshOnUpdate()

    /**
     * Left border of the current component.
     */
    open var borderLeft: Border? by refreshOnUpdate()

    /**
     * Margin of the current component.
     */
    open var margin: CssSize? by refreshOnUpdate()

    /**
     * Top margin of the current component.
     */
    open var marginTop: CssSize? by refreshOnUpdate()

    /**
     * Right margin of the current component.
     */
    open var marginRight: CssSize? by refreshOnUpdate()

    /**
     * Bottom margin of the current component.
     */
    open var marginBottom: CssSize? by refreshOnUpdate()

    /**
     * Left margin of the current component.
     */
    open var marginLeft: CssSize? by refreshOnUpdate()

    /**
     * Padding of the current component.
     */
    open var padding: CssSize? by refreshOnUpdate()

    /**
     * Top padding of the current component.
     */
    open var paddingTop: CssSize? by refreshOnUpdate()

    /**
     * Right padding of the current component.
     */
    open var paddingRight: CssSize? by refreshOnUpdate()

    /**
     * Bottom padding of the current component.
     */
    open var paddingBottom: CssSize? by refreshOnUpdate()

    /**
     * Left padding of the current component.
     */
    open var paddingLeft: CssSize? by refreshOnUpdate()

    /**
     * Text color for the current component.
     */
    open var color: Color? by refreshOnUpdate()

    /**
     * Text color for the current component given in hex format (write only).
     *
     * This property gives a convenient way to set the value of [color] property e.g.:
     *
     * c.colorHex = 0x00ff00
     *
     * The value read from this property is always null.
     */
    open var colorHex: Int?
        get() = null
        set(value) {
            color = if (value != null) Color.hex(value) else null
        }

    /**
     * Text color for the current component given with named constant (write only).
     *
     * This property gives a convenient way to set the value of [color] property e.g.:
     *
     * c.colorName = Col.GREEN
     *
     * The value read from this property is always null.
     */
    open var colorName: Col?
        get() = null
        set(value) {
            color = if (value != null) Color.name(value) else null
        }

    /**
     * Opacity of the current component.
     */
    open var opacity: Double? by refreshOnUpdate()

    /**
     * Background of the current component.
     */
    open var background: Background? by refreshOnUpdate()

    /**
     * CSS Text direction of the current component.
     */
    open var textDirection: Direction? by refreshOnUpdate()

    /**
     * CSS Text letter spacing of the current component.
     */
    open var letterSpacing: CssSize? by refreshOnUpdate()

    /**
     * CSS Text line height of the current component.
     */
    open var lineHeight: CssSize? by refreshOnUpdate()

    /**
     * CSS Text align of the current component.
     */
    open var textAlign: TextAlign? by refreshOnUpdate()

    /**
     * CSS Text decoration of the current component.
     */
    open var textDecoration: TextDecoration? by refreshOnUpdate()

    /**
     * CSS Text indent of the current component.
     */
    open var textIndent: CssSize? by refreshOnUpdate()

    /**
     * CSS Text shadow of the current component.
     */
    open var textShadow: TextShadow? by refreshOnUpdate()

    /**
     * CSS Text transform of the current component.
     */
    open var textTransform: TextTransform? by refreshOnUpdate()

    /**
     * CSS Text overflow of the current component.
     */
    open var textOverflow: TextOverflow? by refreshOnUpdate()

    /**
     * CSS Text unicode-bidi of the current component.
     */
    open var unicodeBidi: UnicodeBidi? by refreshOnUpdate()

    /**
     * CSS Text vertical align of the current component.
     */
    open var verticalAlign: VerticalAlign? by refreshOnUpdate()

    /**
     * CSS Text white space of the current component.
     */
    open var whiteSpace: WhiteSpace? by refreshOnUpdate()

    /**
     * CSS Text word spacing of the current component.
     */
    open var wordSpacing: CssSize? by refreshOnUpdate()

    /**
     * CSS font family of the current component.
     */
    open var fontFamily: String? by refreshOnUpdate()

    /**
     * CSS font size of the current component.
     */
    open var fontSize: CssSize? by refreshOnUpdate()

    /**
     * CSS font style of the current component.
     */
    open var fontStyle: FontStyle? by refreshOnUpdate()

    /**
     * CSS font weight of the current component.
     */
    open var fontWeight: FontWeight? by refreshOnUpdate()

    /**
     * CSS font variant of the current component.
     */
    open var fontVariant: FontVariant? by refreshOnUpdate()

    /**
     * CSS position float of the current component.
     */
    open var float: PosFloat? by refreshOnUpdate()

    /**
     * CSS clear float of the current component.
     */
    open var clear: Clear? by refreshOnUpdate()

    /**
     * CSS word break of the current component.
     */
    open var wordBreak: WordBreak? by refreshOnUpdate()

    /**
     * CSS line break of the current component.
     */
    open var lineBreak: LineBreak? by refreshOnUpdate()

    /**
     * CSS cursor shape over the current component.
     */
    open var cursor: Cursor? by refreshOnUpdate()

    private var snStyleCache: List<StringPair>? = null

    /**
     * @suppress
     * Internal function
     * Re-renders the current component.
     * @return current component
     */
    open fun refresh(): StyledComponent {
        snStyleCache = null
        return this
    }

    internal fun getSnStyleInternal(): List<StringPair> {
        return snStyleCache ?: {
            val s = getSnStyle()
            snStyleCache = s
            s
        }()
    }

    /**
     * Returns the list of String pairs defining CSS style attributes and their values.
     * @return the list of attributes and their values
     */
    @Suppress("ComplexMethod", "LongMethod")
    open fun getSnStyle(): List<StringPair> {
        val cacheKey = getCacheKey()
        return globalStyleCache[cacheKey] ?: run {
            val snstyle = mutableListOf<StringPair>()
            width?.let {
                snstyle.add("width" to it.asString())
            }
            minWidth?.let {
                snstyle.add("min-width" to it.asString())
            }
            maxWidth?.let {
                snstyle.add("max-width" to it.asString())
            }
            height?.let {
                snstyle.add("height" to it.asString())
            }
            minHeight?.let {
                snstyle.add("min-height" to it.asString())
            }
            maxHeight?.let {
                snstyle.add("max-height" to it.asString())
            }
            display?.let {
                snstyle.add("display" to it.display)
            }
            position?.let {
                snstyle.add("position" to it.position)
            }
            top?.let {
                snstyle.add("top" to it.asString())
            }
            left?.let {
                snstyle.add("left" to it.asString())
            }
            right?.let {
                snstyle.add("right" to it.asString())
            }
            bottom?.let {
                snstyle.add("bottom" to it.asString())
            }
            zIndex?.let {
                snstyle.add("z-index" to it.toString())
            }
            overflow?.let {
                snstyle.add("overflow" to it.overflow)
            }
            overflowWrap?.let {
                snstyle.add("overflow-wrap" to it.overflowWrap)
            }
            resize?.let {
                snstyle.add("resize" to it.resize)
            }
            border?.let {
                snstyle.add("border" to it.asString())
            }
            borderTop?.let {
                snstyle.add("border-top" to it.asString())
            }
            borderRight?.let {
                snstyle.add("border-right" to it.asString())
            }
            borderBottom?.let {
                snstyle.add("border-bottom" to it.asString())
            }
            borderLeft?.let {
                snstyle.add("border-left" to it.asString())
            }
            margin?.let {
                snstyle.add("margin" to it.asString())
            }
            marginTop?.let {
                snstyle.add("margin-top" to it.asString())
            }
            marginRight?.let {
                snstyle.add("margin-right" to it.asString())
            }
            marginBottom?.let {
                snstyle.add("margin-bottom" to it.asString())
            }
            marginLeft?.let {
                snstyle.add("margin-left" to it.asString())
            }
            padding?.let {
                snstyle.add("padding" to it.asString())
            }
            paddingTop?.let {
                snstyle.add("padding-top" to it.asString())
            }
            paddingRight?.let {
                snstyle.add("padding-right" to it.asString())
            }
            paddingBottom?.let {
                snstyle.add("padding-bottom" to it.asString())
            }
            paddingLeft?.let {
                snstyle.add("padding-left" to it.asString())
            }
            color?.let {
                snstyle.add("color" to it.asString())
            }
            opacity?.let {
                snstyle.add("opacity" to it.toString())
            }
            background?.let {
                snstyle.add("background" to it.asString())
            }
            textDirection?.let {
                snstyle.add("direction" to it.direction)
            }
            letterSpacing?.let {
                snstyle.add("letter-spacing" to it.asString())
            }
            lineHeight?.let {
                snstyle.add("line-height" to it.asString())
            }
            textAlign?.let {
                snstyle.add("text-align" to it.textAlign)
            }
            textDecoration?.let {
                snstyle.add("text-decoration" to it.asString())
            }
            textIndent?.let {
                snstyle.add("text-indent" to it.asString())
            }
            textShadow?.let {
                snstyle.add("text-shadow" to it.asString())
            }
            textTransform?.let {
                snstyle.add("text-transform" to it.textTransform)
            }
            textOverflow?.let {
                snstyle.add("text-overflow" to it.textOverflow)
            }
            unicodeBidi?.let {
                snstyle.add("unicode-bidi" to it.unicodeBidi)
            }
            verticalAlign?.let {
                snstyle.add("vertical-align" to it.verticalAlign)
            }
            whiteSpace?.let {
                snstyle.add("white-space" to it.whiteSpace)
            }
            wordSpacing?.let {
                snstyle.add("word-spacing" to it.asString())
            }
            fontFamily?.let {
                snstyle.add("font-family" to it)
            }
            fontSize?.let {
                snstyle.add("font-size" to it.asString())
            }
            fontStyle?.let {
                snstyle.add("font-style" to it.fontStyle)
            }
            fontWeight?.let {
                snstyle.add("font-weight" to it.fontWeight)
            }
            fontVariant?.let {
                snstyle.add("font-variant" to it.fontVariant)
            }
            float?.let {
                snstyle.add("float" to it.posFloat)
            }
            clear?.let {
                snstyle.add("clear" to it.clear)
            }
            wordBreak?.let {
                snstyle.add("word-break" to it.wordBreak)
            }
            lineBreak?.let {
                snstyle.add("line-break" to it.lineBreak)
            }
            cursor?.let {
                snstyle.add("cursor" to it.cursor)
            }
            if (customStyles.isNotEmpty()) {
                snstyle += customStyles.toList()
            }
            globalStyleCache[cacheKey] = snstyle
            return snstyle
        }
    }

    /**
     * Returns the value of a custom CSS style.
     * @param name the name of the style
     * @return the value of the style
     */
    fun getStyle(name: String): String? {
        return this.customStyles[name]
    }

    /**
     * Sets the value of a custom CSS style.
     * @param name the name of the style
     * @param value the value of the style
     */
    fun setStyle(name: String, value: String): StyledComponent {
        this.customStyles[name] = value
        refresh()
        return this
    }

    /**
     * Removes the value of a custom CSS style.
     * @param name the name of the style
     */
    fun removeStyle(name: String): StyledComponent {
        this.customStyles.remove(name)
        refresh()
        return this
    }

    protected open fun getCacheKey(): String {
        return ((propertyValues.map {
            it.toString()
        }) + (customStyles.map {
            it.toString()
        })).joinToString("###KvSep###")
    }

    private fun <T> refreshOnUpdate(refreshFunction: ((T) -> Unit) = { this.refresh() }) =
        RefreshDelegateProvider<T>(null, refreshFunction)

    private fun <T> refreshOnUpdate(initialValue: T, refreshFunction: ((T) -> Unit) = { this.refresh() }) =
        RefreshDelegateProvider(initialValue, refreshFunction)

    private inner class RefreshDelegateProvider<T>(
        private val initialValue: T?, private val refreshFunction: (T) -> Unit
    ) {
        operator fun provideDelegate(thisRef: Any?, prop: KProperty<*>): RefreshDelegate<T> {
            if (initialValue != null) propertyValues[prop.name] = initialValue
            return RefreshDelegate(refreshFunction)
        }
    }

    private inner class RefreshDelegate<T>(private val refreshFunction: ((T) -> Unit)) {
        @Suppress("UNCHECKED_CAST")
        operator fun getValue(thisRef: StyledComponent, property: KProperty<*>): T {
            val value = propertyValues[property.name]
            return if (value != null) {
                value as T
            } else {
                null as T
            }
        }

        operator fun setValue(thisRef: StyledComponent, property: KProperty<*>, value: T) {
            propertyValues[property.name] = value
            refreshFunction(value)
        }
    }

    companion object {
        internal val globalStyleCache = Cache<String, List<StringPair>>()
    }
}

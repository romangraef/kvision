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
package pl.treksoft.kvision.form.spinner

import pl.treksoft.kvision.core.Container
import pl.treksoft.kvision.core.StringBoolPair
import pl.treksoft.kvision.core.Widget
import pl.treksoft.kvision.form.FieldLabel
import pl.treksoft.kvision.form.FormHorizontalRatio
import pl.treksoft.kvision.form.InvalidFeedback
import pl.treksoft.kvision.form.NumberFormControl
import pl.treksoft.kvision.html.ButtonStyle
import pl.treksoft.kvision.panel.SimplePanel
import pl.treksoft.kvision.state.ObservableState
import pl.treksoft.kvision.utils.SnOn

/**
 * The form field component for spinner control.
 *
 * @constructor
 * @param value spinner value
 * @param name the name attribute of the generated HTML input element
 * @param min minimal value
 * @param max maximal value
 * @param step step value (default 1)
 * @param decimals number of decimal digits (default 0)
 * @param buttonsType spinner buttons type
 * @param forceType spinner force rounding type
 * @param label label text bound to the input element
 * @param rich determines if [label] can contain HTML code
 */
open class Spinner(
    value: Number? = null, name: String? = null, min: Number? = null, max: Number? = null, step: Number = DEFAULT_STEP,
    decimals: Int = 0, val buttonsType: ButtonsType = ButtonsType.VERTICAL,
    forceType: ForceType = ForceType.NONE, buttonStyle: ButtonStyle? = null, label: String? = null,
    rich: Boolean = false
) : SimplePanel(setOf("form-group")), NumberFormControl, ObservableState<Number?> {

    /**
     * Spinner value.
     */
    override var value
        get() = input.value
        set(value) {
            input.value = value
        }
    /**
     * The value attribute of the generated HTML input element.
     *
     * This value is placed directly in generated HTML code, while the [value] property is dynamically
     * bound to the spinner input value.
     */
    var startValue
        get() = input.startValue
        set(value) {
            input.startValue = value
        }
    /**
     * Minimal value.
     */
    var min
        get() = input.min
        set(value) {
            input.min = value
        }
    /**
     * Maximal value.
     */
    var max
        get() = input.max
        set(value) {
            input.max = value
        }
    /**
     * Step value.
     */
    var step
        get() = input.step
        set(value) {
            input.step = value
        }
    /**
     * Number of decimal digits value.
     */
    var decimals
        get() = input.decimals
        set(value) {
            input.decimals = value
        }
    /**
     * Spinner force rounding type.
     */
    var forceType
        get() = input.forceType
        set(value) {
            input.forceType = value
        }
    /**
     * The style of the up/down buttons.
     */
    var buttonStyle
        get() = input.buttonStyle
        set(value) {
            input.buttonStyle = value
        }
    /**
     * The placeholder for the spinner input.
     */
    var placeholder
        get() = input.placeholder
        set(value) {
            input.placeholder = value
        }
    /**
     * Determines if the spinner is automatically focused.
     */
    var autofocus
        get() = input.autofocus
        set(value) {
            input.autofocus = value
        }
    /**
     * Determines if the spinner is read-only.
     */
    var readonly
        get() = input.readonly
        set(value) {
            input.readonly = value
        }
    /**
     * The label text bound to the spinner input element.
     */
    var label
        get() = flabel.content
        set(value) {
            flabel.content = value
        }
    /**
     * Determines if [label] can contain HTML code.
     */
    var rich
        get() = flabel.rich
        set(value) {
            flabel.rich = value
        }

    override var validatorError: String?
        get() = super.validatorError
        set(value) {
            super.validatorError = value
            if (value != null) {
                input.addSurroundingCssClass("is-invalid")
            } else {
                input.removeSurroundingCssClass("is-invalid")
            }
        }

    protected val idc = "kv_form_spinner_$counter"
    final override val input: SpinnerInput =
        SpinnerInput(value, min, max, step, decimals, buttonsType, forceType, buttonStyle)
            .apply {
                this.id = idc
                this.name = name
            }
    final override val flabel: FieldLabel = FieldLabel(idc, label, rich, setOf("control-label"))
    final override val invalidFeedback: InvalidFeedback = InvalidFeedback().apply { visible = false }

    init {
        @Suppress("LeakingThis")
        input.eventTarget = this
        this.addInternal(flabel)
        this.addInternal(input)
        this.addInternal(invalidFeedback)
        counter++
    }

    override fun getSnClass(): List<StringBoolPair> {
        val cl = super.getSnClass().toMutableList()
        if (validatorError != null) {
            cl.add("text-danger" to true)
        }
        return cl
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Widget> setEventListener(block: SnOn<T>.() -> Unit): Int {
        return input.setEventListener(block)
    }

    override fun removeEventListener(id: Int): Widget {
        input.removeEventListener(id)
        return this
    }

    override fun removeEventListeners(): Widget {
        input.removeEventListeners()
        return this
    }

    override fun getValueAsString(): String? {
        return input.getValueAsString()
    }

    /**
     * Change value in plus.
     */
    open fun spinUp(): Spinner {
        input.spinUp()
        return this
    }

    /**
     * Change value in minus.
     */
    open fun spinDown(): Spinner {
        input.spinDown()
        return this
    }

    override fun focus() {
        input.focus()
    }

    override fun blur() {
        input.blur()
    }

    override fun styleForHorizontalFormPanel(horizontalRatio: FormHorizontalRatio) {
        addCssClass("row")
        flabel.addCssClass("col-sm-${horizontalRatio.labels}")
        flabel.addCssClass("col-form-label")
        input.addSurroundingCssClass("col-sm-${horizontalRatio.fields}")
        invalidFeedback.addCssClass("offset-sm-${horizontalRatio.labels}")
        invalidFeedback.addCssClass("col-sm-${horizontalRatio.fields}")
    }

    override fun getState(): Number? = input.getState()

    override fun subscribe(observer: (Number?) -> Unit): () -> Unit {
        return input.subscribe(observer)
    }

    companion object {
        internal var counter = 0
    }
}

/**
 * DSL builder extension function.
 *
 * It takes the same parameters as the constructor of the built component.
 */
fun Container.spinner(
    value: Number? = null,
    name: String? = null,
    min: Number? = null,
    max: Number? = null,
    step: Number = DEFAULT_STEP,
    decimals: Int = 0,
    buttonsType: ButtonsType = ButtonsType.VERTICAL,
    forceType: ForceType = ForceType.NONE,
    buttonStyle: ButtonStyle? = null,
    label: String? = null,
    rich: Boolean = false,
    init: (Spinner.() -> Unit)? = null
): Spinner {
    val spinner =
        Spinner(value, name, min, max, step, decimals, buttonsType, forceType, buttonStyle, label, rich).apply {
            init?.invoke(
                this
            )
        }
    this.add(spinner)
    return spinner
}

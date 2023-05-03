package com.suresh.appkeyboard

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class AppKeyboard(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet), View.OnClickListener {
    private var inputConnection: InputConnection? = null

    private val showSymbols: Boolean
    private val showCaps: Boolean
    private val showCapsButton: Boolean

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        orientation = VERTICAL
        gravity = Gravity.CENTER

        val attributes =
            context.theme.obtainStyledAttributes(attributeSet, R.styleable.AppKeyboard, 0, 0)
        try {
            showSymbols = attributes.getBoolean(R.styleable.AppKeyboard_showSymbols, false)
            showCaps = attributes.getBoolean(R.styleable.AppKeyboard_showCaps, false)
            showCapsButton = attributes.getBoolean(R.styleable.AppKeyboard_showCapsButton, false)
        } finally {
            attributes.recycle()
        }

        if (showCaps) {
            addView(getKeyboardLayout(createUpperCaseRows()))
        } else {
            addView(getKeyboardLayout(createLowerCaseRows()))
        }

    }

    fun setInputConnection(ic: InputConnection?) {
        inputConnection = ic!!
    }

    private fun getKeyboardLayout(rows: List<LinearLayout>): LinearLayout {
        val column = LinearLayout(context)
        column.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        column.orientation = VERTICAL
        column.gravity = Gravity.CENTER

        for (linearLayout in rows) {
            column.addView(linearLayout)
        }
        return column
    }

    private fun createNumbersRow(): LinearLayout {
        val rowNumbers = ArrayList<View>()
        rowNumbers.add(createButton("1", Color.LTGRAY, true, ""))
        rowNumbers.add(createButton("2", Color.LTGRAY, true, ""))
        rowNumbers.add(createButton("3", Color.LTGRAY, true, ""))
        rowNumbers.add(createButton("4", Color.LTGRAY, true, ""))
        rowNumbers.add(createButton("5", Color.LTGRAY, true, ""))
        rowNumbers.add(createButton("6", Color.LTGRAY, true, ""))
        rowNumbers.add(createButton("7", Color.LTGRAY, true, ""))
        rowNumbers.add(createButton("8", Color.LTGRAY, true, ""))
        rowNumbers.add(createButton("9", Color.LTGRAY, true, ""))
        rowNumbers.add(createButton("0", Color.LTGRAY, true, ""))
        return createRow(rowNumbers)
    }

    private fun createUpperCaseRows(): List<LinearLayout> {
        val rowCharOne = ArrayList<View>()
        rowCharOne.add(createButton("Q", Color.WHITE, true, ""))
        rowCharOne.add(createButton("W", Color.WHITE, true, ""))
        rowCharOne.add(createButton("E", Color.WHITE, true, ""))
        rowCharOne.add(createButton("R", Color.WHITE, true, ""))
        rowCharOne.add(createButton("T", Color.WHITE, true, ""))
        rowCharOne.add(createButton("Y", Color.WHITE, true, ""))
        rowCharOne.add(createButton("U", Color.WHITE, true, ""))
        rowCharOne.add(createButton("I", Color.WHITE, true, ""))
        rowCharOne.add(createButton("O", Color.WHITE, true, ""))
        rowCharOne.add(createButton("P", Color.WHITE, true, ""))

        val rowCharTwo = ArrayList<View>()
        rowCharTwo.add(createButton("A", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("S", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("D", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("F", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("G", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("H", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("J", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("K", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("L", Color.WHITE, true, ""))

        val rowCharThree = ArrayList<View>()
        if (showCapsButton)
            rowCharThree.add(
                createButton(
                    KeyboardConstants.SYMBOL_UP_ARROW,
                    Color.DKGRAY,
                    false,
                    ""
                )
            )
        rowCharThree.add(createButton("Z", Color.WHITE, true, ""))
        rowCharThree.add(createButton("X", Color.WHITE, true, ""))
        rowCharThree.add(createButton("C", Color.WHITE, true, ""))
        rowCharThree.add(createButton("V", Color.WHITE, true, ""))
        rowCharThree.add(createButton("B", Color.WHITE, true, ""))
        rowCharThree.add(createButton("N", Color.WHITE, true, ""))
        rowCharThree.add(createButton("M", Color.WHITE, true, ""))
        rowCharThree.add(createButton(KeyboardConstants.SYMBOL_BACKSPACE, Color.DKGRAY, false, ""))

        val rowCharFour = ArrayList<View>()
        if (showSymbols)
            rowCharFour.add(createButton("? ;", Color.DKGRAY, false, ""))
        rowCharFour.add(createButton(",", Color.WHITE, true, ""))
        rowCharFour.add(createButton(KeyboardConstants.SYMBOL_EMOJI, Color.WHITE, true, ""))
        rowCharFour.add(createButton(KeyboardConstants.SYMBOL_SPACE, Color.WHITE, true, ""))
        rowCharFour.add(createButton(".", Color.WHITE, true, ""))
        rowCharFour.add(createButton(KeyboardConstants.SYMBOL_ENTER, Color.WHITE, true, ""))

        val rows = ArrayList<LinearLayout>()
        rows.add(createNumbersRow())
        rows.add(createRow(rowCharOne))
        rows.add(createRow(rowCharTwo))
        rows.add(createRow(rowCharThree))
        rows.add(createRow(rowCharFour))

        return rows
    }

    private fun createLowerCaseRows(): List<LinearLayout> {
        val rowCharOne = ArrayList<View>()
        rowCharOne.add(createButton("q", Color.WHITE, true, ""))
        rowCharOne.add(createButton("w", Color.WHITE, true, ""))
        rowCharOne.add(createButton("e", Color.WHITE, true, ""))
        rowCharOne.add(createButton("r", Color.WHITE, true, ""))
        rowCharOne.add(createButton("t", Color.WHITE, true, ""))
        rowCharOne.add(createButton("y", Color.WHITE, true, ""))
        rowCharOne.add(createButton("u", Color.WHITE, true, ""))
        rowCharOne.add(createButton("i", Color.WHITE, true, ""))
        rowCharOne.add(createButton("o", Color.WHITE, true, ""))
        rowCharOne.add(createButton("p", Color.WHITE, true, ""))

        val rowCharTwo = ArrayList<View>()
        rowCharTwo.add(createButton("a", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("s", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("d", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("f", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("g", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("h", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("j", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("k", Color.WHITE, true, ""))
        rowCharTwo.add(createButton("l", Color.WHITE, true, ""))

        val rowCharThree = ArrayList<View>()
        if (showCapsButton)
            rowCharThree.add(
                createButton(
                    KeyboardConstants.SYMBOL_UP_ARROW_DASH,
                    Color.DKGRAY,
                    false,
                    ""
                )
            )
        rowCharThree.add(createButton("z", Color.WHITE, true, ""))
        rowCharThree.add(createButton("x", Color.WHITE, true, ""))
        rowCharThree.add(createButton("c", Color.WHITE, true, ""))
        rowCharThree.add(createButton("v", Color.WHITE, true, ""))
        rowCharThree.add(createButton("b", Color.WHITE, true, ""))
        rowCharThree.add(createButton("n", Color.WHITE, true, ""))
        rowCharThree.add(createButton("m", Color.WHITE, true, ""))
        rowCharThree.add(createButton(KeyboardConstants.SYMBOL_BACKSPACE, Color.DKGRAY, false, ""))

        val rowCharFour = ArrayList<View>()
        if (showSymbols)
            rowCharFour.add(createButton("? ;", Color.DKGRAY, false, ""))
        rowCharFour.add(createButton(",", Color.WHITE, true, ""))
        rowCharFour.add(createButton(KeyboardConstants.SYMBOL_EMOJI, Color.WHITE, true, ""))
        rowCharFour.add(createButton(KeyboardConstants.SYMBOL_SPACE, Color.WHITE, true, ""))
        rowCharFour.add(createButton(".", Color.WHITE, true, ""))
        rowCharFour.add(createButton(KeyboardConstants.SYMBOL_ENTER, Color.WHITE, true, ""))

        val rows = ArrayList<LinearLayout>()
        rows.add(createNumbersRow())
        rows.add(createRow(rowCharOne))
        rows.add(createRow(rowCharTwo))
        rows.add(createRow(rowCharThree))
        rows.add(createRow(rowCharFour))

        return rows
    }

    private fun createSymbolsRows(): List<LinearLayout> {
        val rowSymbolOne = ArrayList<View>()
        rowSymbolOne.add(createButton("@", Color.WHITE, true, ""))
        rowSymbolOne.add(createButton("#", Color.WHITE, true, ""))
        rowSymbolOne.add(createButton("$", Color.WHITE, true, ""))
        rowSymbolOne.add(createButton("_", Color.WHITE, true, ""))
        rowSymbolOne.add(createButton("&", Color.WHITE, true, ""))
        rowSymbolOne.add(createButton("-", Color.WHITE, true, ""))
        rowSymbolOne.add(createButton("+", Color.WHITE, true, ""))
        rowSymbolOne.add(createButton("(", Color.WHITE, true, ""))
        rowSymbolOne.add(createButton(")", Color.WHITE, true, ""))
        rowSymbolOne.add(createButton("/", Color.WHITE, true, ""))

        val rowSymbolTwo = ArrayList<View>()
        rowSymbolTwo.add(createButton("*", Color.WHITE, true, ""))
        rowSymbolTwo.add(createButton("\"", Color.WHITE, true, ""))
        rowSymbolTwo.add(createButton("'", Color.WHITE, true, ""))
        rowSymbolTwo.add(createButton(":", Color.WHITE, true, ""))
        rowSymbolTwo.add(createButton(";", Color.WHITE, true, ""))
        rowSymbolTwo.add(createButton("!", Color.WHITE, true, ""))
        rowSymbolTwo.add(createButton("?", Color.WHITE, true, ""))
        rowSymbolTwo.add(createButton("~", Color.WHITE, true, ""))
        rowSymbolTwo.add(createButton("`", Color.WHITE, true, ""))
        rowSymbolTwo.add(createButton("|", Color.WHITE, true, ""))

        val rowSymbolThree = ArrayList<View>()
        rowSymbolThree.add(createButton("^", Color.WHITE, true, ""))
        rowSymbolThree.add(createButton("{", Color.WHITE, true, ""))
        rowSymbolThree.add(createButton("}", Color.WHITE, true, ""))
        rowSymbolThree.add(createButton("\\", Color.WHITE, true, ""))
        rowSymbolThree.add(createButton("%", Color.WHITE, true, ""))
        rowSymbolThree.add(createButton("[", Color.WHITE, true, ""))
        rowSymbolThree.add(createButton("]", Color.WHITE, true, ""))
        rowSymbolThree.add(createButton("<", Color.WHITE, true, ""))
        rowSymbolThree.add(createButton(">", Color.WHITE, true, ""))
        rowSymbolThree.add(
            createButton(
                KeyboardConstants.SYMBOL_BACKSPACE,
                Color.DKGRAY,
                false,
                ""
            )
        )
        val rowSymbolFour = ArrayList<View>()
        if (showSymbols)
            rowSymbolFour.add(createButton(KeyboardConstants.SYMBOL_AA, Color.DKGRAY, false, ""))
        rowSymbolFour.add(createButton(",", Color.WHITE, true, ""))
        rowSymbolFour.add(createButton("=", Color.WHITE, true, ""))
        rowSymbolFour.add(createButton(KeyboardConstants.SYMBOL_SPACE, Color.WHITE, true, ""))
        rowSymbolFour.add(createButton(".", Color.WHITE, true, ""))
        rowSymbolFour.add(createButton(KeyboardConstants.SYMBOL_ENTER, Color.WHITE, true, ""))

        val rows = ArrayList<LinearLayout>()
        rows.add(createNumbersRow())
        rows.add(createRow(rowSymbolOne))
        rows.add(createRow(rowSymbolTwo))
        rows.add(createRow(rowSymbolThree))
        rows.add(createRow(rowSymbolFour))

        return rows
    }

    private fun createButton(
        text: String,
        backgroundColor: Int,
        isTextBlack: Boolean,
        tag: String
    ): MaterialButton {
        //TODO: Update and validate button sizes in all screens
        //TODO: Update the Space size
        val button = MaterialButton(context)
        button.isAllCaps = false
        button.textSize = 20.0f
        button.setTextColor(
            if (isTextBlack) {
                context.getColor(R.color.black)
            } else {
                context.getColor(R.color.white)
            }
        )
        button.text = text
        button.setBackgroundColor(backgroundColor)
        button.rippleColor = ColorStateList.valueOf(Color.LTGRAY)
        button.cornerRadius = 9
        button.tag = tag

        val dm = resources.displayMetrics
        val width =
            KeyboardUtils.pxToDp(context, (((KeyboardUtils.getScreenWidth() / 10)).toFloat() - 15))
        val dp80InPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, dm).toInt()
        val dp85InPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 47f, dm).toInt()
        val dp5InPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, dm).toInt()

        val params = when (text) {
            KeyboardConstants.SYMBOL_SPACE -> {
                LayoutParams(180, dp85InPx)
            }

            else -> {
                LayoutParams(dp80InPx, dp85InPx)
            }
        }
        params.setMargins(dp5InPx, dp5InPx, dp5InPx, dp5InPx)
        button.layoutParams = params
        button.setPadding(0, 0, 0, 0)
        button.setOnClickListener(this)
        return button
    }

    private fun createRow(buttons: List<View>): LinearLayout {
        val row = LinearLayout(context)
        row.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        row.orientation = HORIZONTAL
        row.gravity = Gravity.CENTER

        for (button in buttons) {
            row.addView(button)
        }
        return row
    }

    override fun onClick(view: View?) {
        if (inputConnection == null)
            return

        val button = view as MaterialButton
        val value: String = (button.text as String)
        val tag: Any? = (button.tag)
        if (value == KeyboardConstants.SYMBOL_BACKSPACE) {
            val selectedText = inputConnection?.getSelectedText(0)
            if (TextUtils.isEmpty(selectedText)) {
                inputConnection?.deleteSurroundingText(1, 0)
            } else {
                inputConnection?.commitText("", 1)
            }
        } else if (value == KeyboardConstants.SYMBOL_UP_ARROW) {
            removeAllViews()
            addView(getKeyboardLayout(createLowerCaseRows()))
        } else if (value == KeyboardConstants.SYMBOL_UP_ARROW_DASH) {
            removeAllViews()
            addView(getKeyboardLayout(createUpperCaseRows()))
        } else if (value == KeyboardConstants.SYMBOL_ENTER) {
            inputConnection?.commitText("\n", 1)
        } else if (value == KeyboardConstants.SYMBOL_EMOJI) {
            Toast.makeText(context, "Show Emoji Layout", Toast.LENGTH_SHORT).show()
        } else if (value == "? ;") {
            removeAllViews()
            addView(getKeyboardLayout(createSymbolsRows()))
        } else if (value == KeyboardConstants.SYMBOL_AA) {
            removeAllViews()
            addView(getKeyboardLayout(createLowerCaseRows()))
        } else if (value == KeyboardConstants.SYMBOL_SPACE) {
            inputConnection?.commitText(KeyboardConstants.SYMBOL_SPACE, 1)
        } else {
            inputConnection?.commitText(value, 1)
            if (KeyboardConstants.CAPITAL_LETTERS.contains(value) && !showCaps) {
                removeAllViews()
                addView(getKeyboardLayout(createLowerCaseRows()))
            }
        }
    }
}
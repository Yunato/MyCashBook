package io.github.yunato.mycashbook.ui.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet

class CurrencyEditText: android.support.v7.widget.AppCompatEditText{

    constructor(context: Context) : super(context)

    constructor(context: Context,
                attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context,
                attrs: AttributeSet,
                defStyleAttr: Int): super(context, attrs, defStyleAttr)

    private var prevText: String = text.toString()

    private val watcher:TextWatcher = object :TextWatcher{
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            prevText = s.toString()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val text = s?.toString() ?: return
            if (prevText.contentEquals(text)) return
            val number = text.replace(",", "").toLongOrNull() ?: return
            val textWithComma = number.toStringWithSeparator()
            if (textWithComma.contentEquals(text)) return

            val countRightFromCursor = text.length - selectionEnd
            setTextKeepState(textWithComma)
            setSelection((textWithComma.length - countRightFromCursor).coerceAtLeast(0))
        }
    }

    init {
        addTextChangedListener(watcher)
    }

    fun Number.toStringWithSeparator(): String = "%,d".format(this)
}

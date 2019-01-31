package io.github.yunato.mycashbook.ui.view

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.Button

class ResizeButton : Button {
    var mTextSize: Float = textSize

    constructor(context: Context?) : super(context)

    constructor(context: Context?,
                attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        resize()
    }

    private fun resize() {
        val MIN_TEXT_SIZE = 10f
        val viewRectAngle = RectAngle(height.toFloat(), width.toFloat())

        while (viewRectAngle.isInclude(getTextRectAngle(mTextSize))) {
            mTextSize += 1f
        }

        while (viewRectAngle.isShorter(getTextRectAngle(mTextSize))) {
            if (MIN_TEXT_SIZE >= mTextSize) {
                mTextSize = MIN_TEXT_SIZE
                break
            }
            mTextSize -= 1f
        }
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize)
    }

    private fun getTextRectAngle(mTextSize: Float): RectAngle {
        val paint = Paint()
        paint.textSize = mTextSize
        val fm = paint.fontMetrics
        return RectAngle(
                Math.abs(fm.top) + (Math.abs(fm.descent)),
                paint.measureText(text.toString()))
    }

    class RectAngle(val height: Float, val width: Float) {
        val isShorter: (RectAngle) -> Boolean = fun(rectAngle: RectAngle) =
                height < rectAngle.height || width < rectAngle.width

        val isInclude: (RectAngle) -> Boolean = fun(rectAngle: RectAngle) =
                height > rectAngle.height && width > rectAngle.width
    }
}

package nimbl3.surveyapp.view.customviews

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView
import android.util.AttributeSet


class CustomTextView : TextView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setFont(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setFont(attrs)
    }

    private fun setFont(attrs: AttributeSet) {
        val font = Typeface.createFromAsset(context.assets, "fonts/Poppins-Light.ttf")
        setTypeface(font, Typeface.NORMAL)
    }
}
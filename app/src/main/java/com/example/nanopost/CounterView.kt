package com.example.nanopost

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.content.withStyledAttributes
import com.example.nanopost.databinding.ViewCounterBinding

class CounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewCounterBinding.inflate(
        LayoutInflater.from(this.context),
        this
    )

    var countText: CharSequence? by binding.count::text
    var labelText: CharSequence? by binding.label::text

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        context.withStyledAttributes(
            set = attrs,
            attrs = R.styleable.CounterView,
            defStyleAttr = defStyleAttr,
        ) {
            countText = getText(R.styleable.CounterView_countText)
            labelText = getText(R.styleable.CounterView_labelText)
        }
    }
}
/* так можно но не обязательно

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
    */

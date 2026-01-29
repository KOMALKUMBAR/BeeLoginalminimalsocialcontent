package com.chat.beelogincompanyassigment.ui.main

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.chat.beelogincompanyassigment.R
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class SegmentedSemiCircularNavView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val items = listOf(
        R.drawable.ic_home,
        R.drawable.ic_explore,
        R.drawable.ic_map,
        R.drawable.ic_profile,
        R.drawable.ic_camera
    )

    private val itemNames = listOf("Feed", "Explore", "Map", "Profile", "Camera")

    // 10-color teal gradient
    private val gradientColors = listOf(
        Color.parseColor("#4AC3C3"),
        Color.parseColor("#38AEB7"),
        Color.parseColor("#2F96AD"),
        Color.parseColor("#28839F"),
        Color.parseColor("#1F708F"),
        Color.parseColor("#185E7F"),
        Color.parseColor("#134F73"),
        Color.parseColor("#0F4468"),
        Color.parseColor("#0A395C"),
        Color.parseColor("#062E4F")
    )

    private val iconPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val labelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = 36f
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    private val centerCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private val highlightPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    private var rotationAngle = 0f
    private var lastTouchAngle = 0f
    private var selectedIndex = 0

    var onItemSelected: ((Int) -> Unit)? = null

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val cx = width / 2f
        val cy = height * 1.25f   // ↓ Reduced height (smaller arc)

        val outerRadius = width / 1.45f
        val innerRadius = width / 1.9f
        val centerRadius = width / 11f

        val sliceAngle = 180f / items.size

        for (i in items.indices) {

            val startAngle = 180f + i * sliceAngle + rotationAngle

            // Gradient
            val g1 = gradientColors[i * 2]
            val g2 = gradientColors[i * 2 + 1]

            val slicePaint = Paint(Paint.ANTI_ALIAS_FLAG)
            slicePaint.shader = SweepGradient(cx, cy, intArrayOf(g1, g2), floatArrayOf(0f, 1f))

            // Rect for arc
            val rect = RectF(
                cx - outerRadius,
                cy - outerRadius,
                cx + outerRadius,
                cy + outerRadius
            )

            // --- Apply visibility (only 3 items visible) ---
            val angleDiff = itemVisibilityAngle(i)
            val alpha = when {
                angleDiff < sliceAngle -> 255     // center item
                angleDiff < sliceAngle * 2 -> 150 // left & right
                else -> 40                        // others faint (your requested value)
            }
            slicePaint.alpha = alpha
            iconPaint.alpha = alpha
            labelPaint.alpha = alpha

            // Draw segment
            canvas.drawArc(rect, startAngle, sliceAngle, true, slicePaint)

            // Icon position
            val mid = Math.toRadians(startAngle + sliceAngle / 2.0)
            val iconRadius = (innerRadius + outerRadius) / 2f
            val ix = (cx + cos(mid) * iconRadius).toFloat()
            val iy = (cy + sin(mid) * iconRadius).toFloat()

            // Draw icon
            val bmp = getBitmap(items[i], (width / 11))
            canvas.drawBitmap(bmp, ix - bmp.width / 2, iy - bmp.height / 2, iconPaint)

            // Label
            canvas.drawText(itemNames[i], ix, iy + bmp.height / 2 + 45f, labelPaint)

            // Highlight selected
            if (i == selectedIndex) {
                canvas.drawCircle(ix, iy, width / 13f, highlightPaint)
            }
        }

        // Center white circle
        canvas.drawCircle(cx, cy, centerRadius, centerCirclePaint)
    }

    // Determine visibility by angle
    private fun itemVisibilityAngle(index: Int): Float {
        val slice = 180f / items.size
        val targetAngle = 180f + index * slice
        val currentAngle = (targetAngle + rotationAngle + 360) % 360
        return kotlin.math.abs(currentAngle - 270f) // center = 270°
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val cx = width / 2f
        val cy = height * 0.80f

        val angle = Math.toDegrees(atan2(event.y - cy, event.x - cx).toDouble()).toFloat()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchAngle = angle
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                var delta = angle - lastTouchAngle
                if (delta > 180) delta -= 360
                if (delta < -180) delta += 360

                rotationAngle += delta
                lastTouchAngle = angle

                updateSelectedIndex()
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP -> {
                snapToNearest()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun updateSelectedIndex() {
        val sliceAngle = 180f / items.size
        val normalized = ((rotationAngle % 180 + 180) % 180)
        selectedIndex = (normalized / sliceAngle).toInt() % items.size
    }

    private fun snapToNearest() {
        val sliceAngle = 180f / items.size
        val normalized = ((rotationAngle % 180 + 180) % 180)

        selectedIndex = (normalized / sliceAngle).toInt() % items.size
        onItemSelected?.invoke(selectedIndex)

        val targetRotation = selectedIndex * sliceAngle

        ObjectAnimator.ofFloat(this, "rotSetter", rotationAngle, targetRotation).apply {
            duration = 300
        }.start()
    }

    fun setRotSetter(v: Float) {
        rotationAngle = v
        invalidate()
    }

    private fun getBitmap(id: Int, size: Int): Bitmap {
        val d = resources.getDrawable(id, null)
        val bmp = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val c = Canvas(bmp)
        d.setBounds(0, 0, size, size)
        d.draw(c)
        return bmp
    }

    fun setSelectedItem(index: Int, animated: Boolean = true) {
        if (index in items.indices) {
            selectedIndex = index
            val sliceAngle = 180f / items.size
            val targetRotation = index * sliceAngle

            if (animated) {
                ObjectAnimator.ofFloat(this, "rotSetter", rotationAngle, targetRotation).apply {
                    duration = 300
                }.start()
            } else {
                rotationAngle = targetRotation
                invalidate()
            }
        }
    }
}
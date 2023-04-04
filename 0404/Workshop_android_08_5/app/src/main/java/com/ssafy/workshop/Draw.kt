package com.ssafy.workshop

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

data class Point(var x:Float, var y:Float, var isContinue:Boolean, var color :Int)

private const val TAG = "Draw_싸피"
class Draw : View {
    var list = arrayListOf<Point>()
    private var paint = Paint()
    var currentColor = 0

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}

    init {
        currentColor = Color.GREEN
    }

    fun setPaint(p: Paint) {
        paint = p
        Log.d(TAG, "setPaint: colorChanged!")
    }

    fun resetList() {
        list = arrayListOf<Point>()
        Log.d(TAG, "resetList: deleted!")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        paint = Paint()

        paint.strokeWidth = 10f

        list.forEachIndexed { index, point ->
            if (point.isContinue) {
                Log.d(TAG, "onDraw: ${paint.color}")
                Log.d(TAG, "onDraw: ${point.color}")
                paint.color = point.color
                canvas.drawLine(list[index - 1].x, list[index - 1].y, point.x, point.y, paint)
                // 검 검 검, 새로운 색을 누르면
                // 색상값을 paint.color
            }
        }
    }
        override fun onTouchEvent(event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    list.add(Point(event.x, event.y, false, currentColor))
//                    list.add(Point(event.x, event.y, false, paint.color))
                    Log.d(TAG, "onTouchEvent: DOWN")
                }
                MotionEvent.ACTION_MOVE -> {
                    list.add(Point(event.x, event.y, true, currentColor))
//                    list.add(Point(event.x, event.y, false, paint.color))
                    Log.d(TAG, "onTouchEvent: MOVE")
                }
                MotionEvent.ACTION_UP -> {
                    list.add(Point(event.x, event.y, false, currentColor))
                    Log.d(TAG, "onTouchEvent: UP")
                }
            }
            invalidate()
            // 전체 배열 초기화

            //touch이후에 event를 전달할것인가? true: 여기서 종료. false :뒤로 전달.
            // touch -> click -> longclick
            return true
        }


}

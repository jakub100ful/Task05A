package com.example.task05a

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

import com.example.logic.StudentGame

class GameView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var mStudentGame: StudentGame = StudentGame(8,12)
    private val colCount = mStudentGame.mColumns
    private val rowCount = mStudentGame.mRows

    private var mGridPaint: Paint
    private var mNoPlayerPaint: Paint
    private var mPlayer1Paint: Paint
    private var mPlayer2Paint: Paint
    
    init {
        mGridPaint = Paint().apply {
            style = Paint.Style.FILL
            color = Color.BLUE
        }

        mNoPlayerPaint = Paint().apply {
            style = Paint.Style.FILL
            color = Color.WHITE
        }

        mPlayer1Paint = Paint().apply {
            style = Paint.Style.FILL
            color = Color.RED
        }

        mPlayer2Paint = Paint().apply {
            style = Paint.Style.FILL
            color = Color.YELLOW
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val chosenDiameter: Float
        var tokenAtPos: Int
        var paint: Paint

        val viewWidth: Float = width.toFloat()
        val viewHeight: Float = height.toFloat()

        val diameterX: Float = viewWidth / colCount.toFloat()
        val diameterY: Float = viewHeight / rowCount.toFloat()

        if (diameterX < diameterY)
            chosenDiameter = diameterX
        else
            chosenDiameter = diameterY

        canvas.drawRect(0.toFloat(), 0.toFloat(), viewWidth, viewHeight, mGridPaint)

        val radius = chosenDiameter / 2

        // Draw the circles on the game board
        for (col in 0 until colCount) {
            for (row in 0 until rowCount) {
                tokenAtPos = mStudentGame.getToken(col, row)

                if (tokenAtPos == 1) {
                    paint = mPlayer1Paint
                } else if (tokenAtPos == 2) {
                    paint = mPlayer2Paint
                } else {
                    paint = mNoPlayerPaint
                }

                // Calculate the coordinates of the circle
                val cx = chosenDiameter * col + radius
                val cy = chosenDiameter * row + radius

                canvas.drawCircle(cx, cy, radius, paint)
            }
        }
    }
}


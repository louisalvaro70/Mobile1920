package random.android.minesweeper.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

import random.android.minesweeper.MainActivity
import random.android.minesweeper.R
import random.android.minesweeper.model.MinesweeperModel

/*
 * Created by random on 9/28/16.
 */

class MinesweeperView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paintBg: Paint
    private val paintLine: Paint
    private val paintBomb: Paint
    private val paintOpen: Paint

    private val flagBitmap: Bitmap
    private val bombBitmap: Bitmap
    private val oneBitmap: Bitmap
    private val twoBitmap: Bitmap
    private val threeBitmap: Bitmap
    private val fourBitmap: Bitmap
    private val fiveBitmap: Bitmap
    private val sixBitmap: Bitmap
    private val sevenBitmap: Bitmap
    private val eightBitmap: Bitmap

    private var gameEnd = false

    init {

        paintBg = Paint()
        paintBg.color = Color.GRAY
        paintBg.style = Paint.Style.FILL

        paintBomb = Paint()

        paintLine = Paint()
        paintLine.color = Color.WHITE

        paintOpen = Paint()
        paintOpen.setARGB(0, 0, 0, 0)

        val options = BitmapFactory.Options()
        options.inScaled = true
        options.inSampleSize = 2

        flagBitmap = BitmapFactory.decodeResource(resources, R.drawable.flag, options)
        bombBitmap = BitmapFactory.decodeResource(resources, R.drawable.bomb, options)
        oneBitmap = BitmapFactory.decodeResource(resources, R.drawable.one, options)
        twoBitmap = BitmapFactory.decodeResource(resources, R.drawable.two, options)
        threeBitmap = BitmapFactory.decodeResource(resources, R.drawable.three, options)
        fourBitmap = BitmapFactory.decodeResource(resources, R.drawable.four, options)
        fiveBitmap = BitmapFactory.decodeResource(resources, R.drawable.five, options)
        sixBitmap = BitmapFactory.decodeResource(resources, R.drawable.six, options)
        sevenBitmap = BitmapFactory.decodeResource(resources, R.drawable.seven, options)
        eightBitmap = BitmapFactory.decodeResource(resources, R.drawable.eight, options)

        MinesweeperModel.getInstance().setMines()
        MinesweeperModel.getInstance().setMineCount()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draws tiles
        drawGameArea(canvas)
        drawModel(canvas)
        drawCover(canvas)
    }

    private fun drawModel(canvas: Canvas) {
        for (i in 0..4) {
            for (j in 0..4) {

                val centerX = (i * width / 5).toFloat()
                val centerY = (j * height / 5).toFloat()

                if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.MINE) {
                    // draws bomb
                    canvas.drawBitmap(bombBitmap, centerX, centerY, paintBomb)
                } else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.ONE) {
                    canvas.drawBitmap(oneBitmap, centerX, centerY, paintBomb)
                } else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.TWO) {
                    canvas.drawBitmap(twoBitmap, centerX, centerY, paintBomb)
                } else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.THREE) {
                    canvas.drawBitmap(threeBitmap, centerX, centerY, paintBomb)
                } else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.FOUR) {
                    canvas.drawBitmap(fourBitmap, centerX, centerY, paintBomb)
                } else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.FIVE) {
                    canvas.drawBitmap(fiveBitmap, centerX, centerY, paintBomb)
                } else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.SIX) {
                    canvas.drawBitmap(sixBitmap, centerX, centerY, paintBomb)
                } else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.SEVEN) {
                    canvas.drawBitmap(sevenBitmap, centerX, centerY, paintBomb)
                } else if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.EIGHT) {
                    canvas.drawBitmap(eightBitmap, centerX, centerY, paintBomb)
                }
            }
        }
        Log.i("MODEL_TAG", "Bombs and Numbers displayed")
    }


    private fun drawCover(canvas: Canvas) {
        for (i in 0..4) {
            for (j in 0..4) {

                val centerX = (i * width / 5).toFloat()
                val centerY = (j * height / 5).toFloat()

                if (MinesweeperModel.getInstance().getCoverContent(i, j) == MinesweeperModel.UNTOUCHED) {
                    canvas.drawRect(centerX, centerY, centerX + width / 5, centerY + height / 5, paintBg)
                } else if (MinesweeperModel.getInstance().getCoverContent(i, j) == MinesweeperModel.TOUCHED) {
                    canvas.drawRect(centerX, centerY, centerX + width / 5, centerY + height / 5, paintOpen)
                    Log.i("COVER_TAG", "Tile [$i] [$j] is opened.")
                } else if (MinesweeperModel.getInstance().getCoverContent(i, j) == MinesweeperModel.FLAG) {
                    canvas.drawRect(centerX, centerY, centerX + width / 5, centerY + height / 5, paintBg)
                    canvas.drawBitmap(flagBitmap, centerX, centerY, paintBomb)
                    Log.i("COVER_TAG", "Tile [$i] [$j] is flagged.")
                }

            }
        }
        Log.i("MODEL_TAG", "Cover displayed")

        canvas.drawLine(0f, (height / 5).toFloat(), width.toFloat(), (height / 5).toFloat(),
                paintLine)
        canvas.drawLine(0f, (2 * height / 5).toFloat(), width.toFloat(),
                (2 * height / 5).toFloat(), paintLine)
        canvas.drawLine(0f, (3 * height / 5).toFloat(), width.toFloat(),
                (3 * height / 5).toFloat(), paintLine)
        canvas.drawLine(0f, (4 * height / 5).toFloat(), width.toFloat(),
                (4 * height / 5).toFloat(), paintLine)

        canvas.drawLine((width / 5).toFloat(), 0f, (width / 5).toFloat(), height.toFloat(),
                paintLine)
        canvas.drawLine((2 * width / 5).toFloat(), 0f, (2 * width / 5).toFloat(), height.toFloat(),
                paintLine)
        canvas.drawLine((3 * width / 5).toFloat(), 0f, (3 * width / 5).toFloat(), height.toFloat(),
                paintLine)
        canvas.drawLine((4 * width / 5).toFloat(), 0f, (4 * width / 5).toFloat(), height.toFloat(),
                paintLine)
        Log.i("MODEL_TAG", "Lines drawn")
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (gameEnd) {
                MinesweeperModel.getInstance().cleanBoard()
                MinesweeperModel.getInstance().setMines()
                MinesweeperModel.getInstance().setMineCount()
                invalidate()
                gameEnd = false
            } else {
                val tX = event.x.toInt() / (width / 5)
                val tY = event.y.toInt() / (height / 5)

                handleCoverTouch(tX, tY)

                winningModel()
            }

            invalidate()
        }
        return super.onTouchEvent(event)
    }

    private fun handleCoverTouch(tX: Int, tY: Int) {
        if (tX < 5 && tY < 5 &&
                MinesweeperModel.getInstance().getCoverContent(tX, tY) == MinesweeperModel.UNTOUCHED &&
                MinesweeperModel.getInstance().actionType == MinesweeperModel.REVEAL) {
            MinesweeperModel.getInstance().setCoverContent(tX, tY, MinesweeperModel.getInstance().touched)
        } else if (tX < 5 && tY < 5 &&
                MinesweeperModel.getInstance().getCoverContent(tX, tY) == MinesweeperModel.UNTOUCHED &&
                MinesweeperModel.getInstance().actionType == MinesweeperModel.FLAG) {
            MinesweeperModel.getInstance().setCoverContent(tX, tY, MinesweeperModel.getInstance().flagged)
        }
    }

    private fun drawGameArea(canvas: Canvas) {
        // border
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBg)

        // four horizontal lines
        canvas.drawLine(0f, (height / 5).toFloat(), width.toFloat(), (height / 5).toFloat(),
                paintLine)
        canvas.drawLine(0f, (2 * height / 5).toFloat(), width.toFloat(),
                (2 * height / 5).toFloat(), paintLine)
        canvas.drawLine(0f, (3 * height / 5).toFloat(), width.toFloat(),
                (3 * height / 5).toFloat(), paintLine)
        canvas.drawLine(0f, (4 * height / 5).toFloat(), width.toFloat(),
                (4 * height / 5).toFloat(), paintLine)

        // four vertical lines
        canvas.drawLine((width / 5).toFloat(), 0f, (width / 5).toFloat(), height.toFloat(),
                paintLine)
        canvas.drawLine((2 * width / 5).toFloat(), 0f, (2 * width / 5).toFloat(), height.toFloat(),
                paintLine)
        canvas.drawLine((3 * width / 5).toFloat(), 0f, (3 * width / 5).toFloat(), height.toFloat(),
                paintLine)
        canvas.drawLine((4 * width / 5).toFloat(), 0f, (4 * width / 5).toFloat(), height.toFloat(),
                paintLine)
    }

    private fun winningModel() {
        if (MinesweeperModel.getInstance().checkAllTiles() && !MinesweeperModel.getInstance().gameLost()) {
            //game won
            (context as MainActivity).showSnackBarWithDelete(
                    "Congratulations you win!")
            gameEnd = true
        } else if (MinesweeperModel.getInstance().gameLost()) {
            (context as MainActivity).showSnackBarWithDelete(
                    "Oh no! You lost!")
            gameEnd = true
        } else if (!MinesweeperModel.getInstance().checkAllTiles() && !MinesweeperModel.getInstance().gameLost()) {

        }
    }


    /*
    public void restartGame() {
        MinesweeperModel.getInstance().cleanBoard();
        MinesweeperModel.getInstance().setMines();
        MinesweeperModel.getInstance().setMineCount();
        invalidate();
    }
    */
}

package random.android.minesweeper.model


import android.util.Log
import java.util.Random

class MinesweeperModel private constructor() {
    internal var rand = Random()

    private val model = arrayOf(shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY))

    var actionType = REVEAL
        private set

    private var minesLeft = 0
    private var globalMineCount = 0
    private var giftsOpened = 0
    private var points = 0

    private val cover = arrayOf(shortArrayOf(UNTOUCHED, UNTOUCHED, UNTOUCHED, UNTOUCHED, UNTOUCHED), shortArrayOf(UNTOUCHED, UNTOUCHED, UNTOUCHED, UNTOUCHED, UNTOUCHED), shortArrayOf(UNTOUCHED, UNTOUCHED, UNTOUCHED, UNTOUCHED, UNTOUCHED), shortArrayOf(UNTOUCHED, UNTOUCHED, UNTOUCHED, UNTOUCHED, UNTOUCHED), shortArrayOf(UNTOUCHED, UNTOUCHED, UNTOUCHED, UNTOUCHED, UNTOUCHED))

    val touched: Short
        get() = TOUCHED

    val flagged: Short
        get() = FLAG

    fun cleanBoard() {
        for (i in 0..4) {
            for (j in 0..4) {
                model[i][j] = EMPTY
                cover[i][j] = UNTOUCHED
            }
        }
        actionType = REVEAL
    }

    fun getFieldContent(x: Int, y: Int): Short {
        return model[x][y]
    }

    fun getCoverContent(x: Int, y: Int): Short {
        return cover[x][y]
    }

    fun setFieldContent(x: Int, y: Int, content: Short): Short {
        model[x][y] = content
        return model[x][y]
    }

    fun setCoverContent(x: Int, y: Int, content: Short): Short {
        cover[x][y] = content
        return cover[x][y]
    }

    fun actionFlag() {
        actionType = FLAG
    }

    fun actionReveal() {
        actionType = REVEAL
    }

    fun setMines() {
        minesLeft = 0
        for (i in 0..4) {
            for (j in 0..4) {
                if (rand.nextInt(3) == 1) {
                    model[i][j] = MINE
                    minesLeft += 1
                }
            }
        }
    }

    fun setMineCount() {
        for (i in 0..4) {
            for (j in 0..4) {
                if (model[i][j] != MINE) {

                    var mineCounter = 0

                    if (i > 0 && j > 0 && model[i - 1][j - 1] == MINE) {
                        mineCounter++
                        globalMineCount++
                    }
                    if (i > 0 && model[i - 1][j] == MINE) {
                        mineCounter++
                        globalMineCount++
                    }
                    if (i > 0 && j < 4 && model[i - 1][j + 1] == MINE) {
                        mineCounter++
                        globalMineCount++
                    }
                    if (j > 0 && model[i][j - 1] == MINE) {
                        mineCounter++
                        globalMineCount++
                    }
                    if (model[i][j] == MINE) {
                        mineCounter++
                        globalMineCount++
                    }
                    if (j < 4 && model[i][j + 1] == MINE) {
                        mineCounter++
                        globalMineCount++
                    }
                    if (i < 4 && j > 0 && model[i + 1][j - 1] == MINE) {
                        mineCounter++
                        globalMineCount++
                    }
                    if (i < 4 && model[i + 1][j] == MINE) {
                        mineCounter++
                        globalMineCount++
                    }
                    if (i < 4 && j < 4 && model[i + 1][j + 1] == MINE) {
                        mineCounter++
                        globalMineCount++
                    }

                    val mineCounterShort = intToShort(mineCounter)

                    model[i][j] = mineCounterShort

                }
            }
        }
    }

    private fun countMines(): Int {
        var mineCounter = 0
        for (i in 0..4) {
            for (j in 0..4) {
                if (model[i][j] == MINE) {
                    mineCounter++
                }
            }
        }
        return mineCounter
    }

    private fun mineRevealed(): Boolean {
        for (i in 0..4) {
            for (j in 0..4) {
                if (model[i][j] == MINE && cover[i][j] == TOUCHED) {
                    Log.i("GameUpdate", "Gift discovered")
                    minesLeft--
                    Log.i("GameUpdate", "Gifts left = " + minesLeft)
                    if (giftsOpened == globalMineCount) {
                        Log.i("GameUpdate", "You win!")
                    }
                    //return true
                }
            }
        }
        return false
    }

    private fun nonmineFlagged(): Boolean {
        for (i in 0..4) {
            for (j in 0..4) {
                if (model[i][j] != MINE && cover[i][j] == FLAG) {
                    Log.i("GameUpdate", "Tile flagged")
                }
            }
        }
        return false
    }

    fun gameLost(): Boolean {
        return mineRevealed() || nonmineFlagged()
    }

    fun checkAllTiles(): Boolean {

        var minesFlagged = 0
        var nonMinesOpened = 0
        var giftsRemaining = globalMineCount - giftsOpened

        for (i in 0..4) {
            for (j in 0..4) {
                if (model[i][j] == MINE && cover[i][j] == FLAG) {
                    minesFlagged++
                }
                if (model[i][j] != MINE && cover[i][j] == TOUCHED) {
                    nonMinesOpened++
                }
                if (model[i][j] == MINE && cover[i][j] == TOUCHED) {
                    points+=50
                    giftsOpened++
                    Log.i("GameUpdate", "Gift opened,  points: " + points)
                    Log.i("GameUpdate", "Gifts left: " + giftsRemaining)
                }
            }
        }
        return minesFlagged == countMines() && nonMinesOpened == 25 - countMines()
    }

    private fun intToShort(x: Int): Short {
        return if (x == 1) {
            ONE
        } else if (x == 2) {
            TWO
        } else if (x == 3) {
            THREE
        } else if (x == 4) {
            FOUR
        } else if (x == 5) {
            FIVE
        } else if (x == 6) {
            SIX
        } else if (x == 7) {
            SEVEN
        } else if (x == 8) {
            EIGHT
        } else {
            EMPTY
        }
    }

    companion object {

        private var instance: MinesweeperModel? = null

        fun getInstance(): MinesweeperModel {
            if (instance == null) {
                instance = MinesweeperModel()
            }
            return instance!!



        }

        val EMPTY: Short = 0

        val ONE: Short = 1
        val TWO: Short = 2
        val THREE: Short = 3
        val FOUR: Short = 4
        val FIVE: Short = 5
        val SIX: Short = 6
        val SEVEN: Short = 7
        val EIGHT: Short = 8

        val MINE: Short = 9

        val REVEAL: Short = 10
        val FLAG: Short = 11

        val TOUCHED: Short = 12
        val UNTOUCHED: Short = 13
    }

}

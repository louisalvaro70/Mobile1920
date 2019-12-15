package random.android.minesweeper

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

import random.android.minesweeper.model.MinesweeperModel

class MainActivity : AppCompatActivity() {

    private var linearLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayout = findViewById(R.id.activity_main) as LinearLayout

        val btnRestart = findViewById(R.id.btnRestart) as Button
        btnRestart.setOnClickListener {
            MinesweeperModel.getInstance().cleanBoard()
            MinesweeperModel.getInstance().setMines()
            MinesweeperModel.getInstance().setMineCount()

            val restartSnackbar = Snackbar.make(linearLayout!!, "Game restarted, board has been reset. Tap a tile to start the new game", Snackbar.LENGTH_LONG)
            restartSnackbar.show()
        }

        val btnFlag = findViewById(R.id.btnFlag) as Button
        btnFlag.setOnClickListener {

            MinesweeperModel.getInstance().actionFlag()

            val flagSnackbar = Snackbar.make(linearLayout!!, "Flag mode: ON (Tap a tile to flag them)", Snackbar.LENGTH_LONG)
            flagSnackbar.show()
        }

        val btnReveal = findViewById(R.id.btnReveal) as Button
        btnReveal.setOnClickListener {
            MinesweeperModel.getInstance().actionReveal()

            val revealSnackbar = Snackbar.make(linearLayout!!, "Flag mode: OFF (Tap a tile to open them)", Snackbar.LENGTH_LONG)
            revealSnackbar.show()
        }

    }

    fun showSnackBarWithDelete(msg: String) {
        Snackbar.make(linearLayout!!, msg,
                Snackbar.LENGTH_LONG).setAction(
                "Restart Game"
        ) {
            // Restart the game
            MinesweeperModel.getInstance().cleanBoard()
            MinesweeperModel.getInstance().setMines()
            MinesweeperModel.getInstance().setMineCount()
        }.show()
    }
}

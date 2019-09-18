package com.random.constraint

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.random.constraint.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var selectedView: View
    private val WinStar = 17301516
    private val DeathStar = 17301515
    private var chosenOne = 1
    var taps = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val boxList: List<Int> = listOf(
            binding.boxOneText.id,
            binding.boxTwoText.id,
            binding.boxThreeText.id,
            binding.boxFourText.id,
            binding.boxFiveText.id,
            binding.boxSixText.id,
            binding.boxSevenText.id
        )
        val randomNumber = Random.nextInt(0,7)
        chosenOne = boxList[randomNumber]
        setListeners()
    }

    private fun makeColored(view: View) {
        if(view.id == chosenOne && taps != 6) {
            view.setBackgroundResource(WinStar)
            selectedView = view
            Toast.makeText(this, "You found the star! Tap it to play again", Toast.LENGTH_LONG).show()
            selectedView.setOnClickListener{
                reset()
            }
        }
        else if (taps != 6){
            taps += 1
        }

        else {
            view.id = chosenOne
            view.setBackgroundResource(DeathStar)
            selectedView = view
            Toast.makeText(this, "You failed. Tap a star to try again", Toast.LENGTH_LONG).show()
            selectedView.setOnClickListener{
                reset()
            }
        }
    }


    private fun setButtonBg(view: View, buttonIcon: Int) {
        view.setBackgroundResource(buttonIcon)
    }


    private fun GameWon() {
        Toast.makeText(this, "You Win! Tap the star to play again", Toast.LENGTH_LONG).show()
        selectedView.setOnClickListener{
            reset()
        }
    }

    private fun reset () {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val boxId: List<Int> = listOf(
            binding.boxOneText.id,
            binding.boxTwoText.id,
            binding.boxThreeText.id,
            binding.boxFourText.id,
            binding.boxFiveText.id,
            binding.boxSixText.id,
            binding.boxSevenText.id

        )
        taps = 0
        val randomNumber = Random.nextInt(0,7)
        chosenOne = boxId[randomNumber]

        val boxOneText = findViewById<TextView>(R.id.box_one_text)
        val boxTwoText = findViewById<TextView>(R.id.box_two_text)
        val boxThreeText = findViewById<TextView>(R.id.box_three_text)
        val boxFourText = findViewById<TextView>(R.id.box_four_text)
        val boxFiveText = findViewById<TextView>(R.id.box_five_text)
        val boxSixText = findViewById<TextView>(R.id.box_six_text)
        val boxSevenText = findViewById<TextView>(R.id.box_seven_text)

        val clickableViews: List<View> =
            listOf(boxOneText, boxTwoText, boxThreeText, boxFourText, boxFiveText,
                boxSixText, boxSevenText)

        for (item in clickableViews) {
            item.setOnClickListener{
                makeColored(it)
            }
            item.setBackgroundColor(Color.WHITE)
        }
    }

    private fun setListeners() {
        val boxOneText = findViewById<TextView>(R.id.box_one_text)
        val boxTwoText = findViewById<TextView>(R.id.box_two_text)
        val boxThreeText = findViewById<TextView>(R.id.box_three_text)
        val boxFourText = findViewById<TextView>(R.id.box_four_text)
        val boxFiveText = findViewById<TextView>(R.id.box_five_text)
        val boxSixText = findViewById<TextView>(R.id.box_six_text)
        val boxSevenText = findViewById<TextView>(R.id.box_seven_text)

        val clickableViews: List<View> =
            listOf(boxOneText, boxTwoText, boxThreeText,
                boxFourText, boxFiveText, boxSixText, boxSevenText)

        for (item in clickableViews) {
            item.setOnClickListener {
                makeColored(it)
            }
        }
    }
}
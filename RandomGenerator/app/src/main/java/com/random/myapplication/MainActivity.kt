/* Week 1
Assignment = Modify the app so that it generates heads or tails
 */

package com.random.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var resultText: TextView
    lateinit var rollButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultText=findViewById(R.id.resultText) as TextView
        rollButton=findViewById(R.id.rollButton) as Button
        rollButton.setOnClickListener {
            rollDice()
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun rollDice() {
        val randomInt=Random().nextInt(2)+0     //Modified so that it generates 0 or 1 instead

        /* Uncomment below line for debug/testing */
        //resultText.text=randomInt.toString()

        //Check the generated number to determine whether it's head or tails
        if (randomInt == 1){
            resultText.text="you will be rich when 25"     //If 1, display this line
        }
        else{
            resultText.text="you are jobless after get fired from the company"     //If 0, display this line
        }


    }
}
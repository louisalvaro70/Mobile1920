/* Week 3
Mission: Create spinner to generate different ipsums and bind them using Data Binding
 */
package com.random.loremgenerator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.random.loremgenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myData: MyData = MyData("Created by me")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val spinnerCheck = findViewById<Spinner>(R.id.spinner)      //Check for any declared spinners
        binding.myData = myData

        val ipsumList = getResources().getStringArray(R.array.dropList)
        binding.creatorButton.setOnClickListener{
            pickCreatorName(it)
        }
        binding.creatorText.setOnClickListener {
            changeCreatorName(it)
        }

        //No spinners detected
        if (spinnerCheck != null) {
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ipsumList)  //Initialize array for dropdown spinner
            binding.spinner.adapter = arrayAdapter  //Bind spinner with layout resource

            //Execute the input from users
            binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                //Users select the item from the dropdown menu
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                    if (position == 0) {
                        binding.loremText.text = getString(R.string.lorem_text)
                    }


                    if (position == 1) {
                        binding.loremText.text = getString(R.string.lorem_text2)
                    }


                    if (position == 2) {
                        binding.loremText.text = getString(R.string.lorem_text3)
                    }


                    if (position == 3) {
                        binding.loremText.text = getString(R.string.lorem_text4)
                    }

                    if (position == 4) {
                        binding.loremText.text = getString(R.string.lorem_text5)
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>) {

                }

            }

        }
    }

    private fun pickCreatorName(view: View) {

        //change text
        binding.apply {
            myData?.creator = creatorFill.text.toString()
            invalidateAll()
            creatorFill.visibility = View.GONE
            creatorButton.visibility = View.GONE
            creatorText.visibility = View.VISIBLE
        }

        //Hide keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun changeCreatorName(view: View) {
        binding.apply {
            creatorText.visibility = View.GONE
            creatorFill.visibility = View.VISIBLE
            creatorButton.visibility = View.VISIBLE
            creatorFill.requestFocus()
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.creatorFill, 0)
    }
}

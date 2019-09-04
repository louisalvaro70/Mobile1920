package com.github.louisalvaro70.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val tag="Main Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(tag,"In onCreate callback")
        val test = findViewById<Button>(R.id.testing)
        test.setOnClickListener{
            val x = Intent(this,Test::class.java)
            startActivity(x)

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag,"In onStart callback")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag,"In onStop callback")
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}

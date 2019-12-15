package com.github.louisalvaro70.lifecycle

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Test : AppCompatActivity() {

    val tag="Test page"

    //lateinit var TextShow: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //TextShow=findViewById(R.id.TextShow) as TextView

        Log.d(tag,"Inside onCreate")
        Toast.makeText(this,"Currently in testing page",Toast.LENGTH_LONG).show()
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
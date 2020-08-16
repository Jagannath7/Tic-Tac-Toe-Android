package com.example.tictoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

const val KEY_1 = "name"
const val KEY_2 = "name1"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameStart.setOnClickListener {
            val i = Intent(this, MainActivity2::class.java)
            i.putExtra(KEY_1,et.text.toString())
            i.putExtra(KEY_2,et1.text.toString())
            startActivity(i)

        }




    }
}
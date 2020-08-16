package com.example.tictoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main2.*

var TURN_COUNT = 0 // keeping the count , till it reaches 9 as there are only 9 boxes in the game.
var PLAYER = true // true for X and false for O

var boardStatus = Array(3){IntArray(3)}
lateinit var board : Array<Array<Button>>

class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val name = intent.getStringExtra(KEY_1)
        tv.text = "${name}'s Turn"


        board = arrayOf(
            arrayOf(button,button1,button2),
            arrayOf(button3,button4,button5),
            arrayOf(button6,button7,button8)
        )

        for (i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        goback.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        reset.setOnClickListener{
            TURN_COUNT = 0
            PLAYER = true
            val name = intent.getStringExtra(KEY_1)
            updateDisplay("${name}'s Turn")
            initalBoard()

        }

        initalBoard()

    }

    private fun initalBoard() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
            }
        }

        for(i in 0..2){
            for(j in 0..2){
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button -> {
                updateValue(row = 0,col=0,player = PLAYER)
            }
            R.id.button1 -> {
                updateValue(row = 0,col=1,player = PLAYER)
            }
            R.id.button2 -> {
                updateValue(row = 0,col=2,player = PLAYER)
            }
            R.id.button3 -> {
                updateValue(row = 1,col=0,player = PLAYER)
            }
            R.id.button4 -> {
                updateValue(row = 1,col=1,player = PLAYER)
            }
            R.id.button5 -> {
                updateValue(row = 1,col=2,player = PLAYER)
            }
            R.id.button6 -> {
                updateValue(row = 2,col=0,player = PLAYER)
            }
            R.id.button7 -> {
                updateValue(row = 2,col=1,player = PLAYER)
            }
            R.id.button8 -> {
                updateValue(row = 2,col=2,player = PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER
        if(PLAYER){
            val name = intent.getStringExtra(KEY_1)
            updateDisplay(" ${name}'s Turn")
        }
        else{
            val name1 = intent.getStringExtra(KEY_2)
            updateDisplay("${name1}'s Turn")
        }
        if(TURN_COUNT == 9){
            updateDisplay("Game is Drawn")
        }
        checkWinner()
    }

    private fun checkWinner() {
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][1] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    val name = intent.getStringExtra(KEY_1)
                    updateDisplay(" ${name} is the Winner")
                }
                else if(boardStatus[i][0]==0){
                    val name1 = intent.getStringExtra(KEY_2)
                    updateDisplay(" ${name1} is the Winner")
                }
            }
        }

        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[1][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    val name = intent.getStringExtra(KEY_1)
                    updateDisplay(" ${name} is the Winner")
                }
                else if(boardStatus[0][i]==0){
                    val name1 = intent.getStringExtra(KEY_2)
                    updateDisplay(" ${name1} is the Winner")
                }
            }
        }

        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][2]){
            if(boardStatus[0][0]==1){
                val name = intent.getStringExtra(KEY_1)
                updateDisplay(" ${name} is the Winner")
            }else if(boardStatus[0][0]==0){
                val name1 = intent.getStringExtra(KEY_2)
                updateDisplay(" ${name1} is the Winner")
            }
        }

        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[1][1]==boardStatus[2][0]){
            if(boardStatus[0][2]==1){
                val name = intent.getStringExtra(KEY_1)
                updateDisplay("${name} is the Winner")
            }else if(boardStatus[2][0]==0){
                val name1 = intent.getStringExtra(KEY_2)
                updateDisplay(" ${name1} is the Winner")
            }
        }
    }

    private fun updateDisplay(displayText: String) {
            tv.text = displayText
            if(displayText.contains("Winner")){
                disableButton()
                }
            }

    private fun disableButton() {
        for (i in board){
            for(button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if(player) "X" else "O"
        val value = if(player) 1 else 0

        board[row][col].apply{
            isEnabled = false
            setText(text)
        }

        boardStatus[row][col] = value
    }
}
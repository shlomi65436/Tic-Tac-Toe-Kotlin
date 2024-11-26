package com.example.tictactoe

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    enum class Turn{
        CIRCLE,
        CROSS
    }
    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var boardList = mutableListOf<Button>()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    fun boardTapped(view : View){
        if(view !is Button)
            return
        addToBoard(view)
        if(checkWin("X")){
            result("X Wins")
        }
        if(checkWin("O")){
            result("O Wins")
        }

        if(fullBoard()){
            result("Draw")
        }
    }
    private fun checkWin(s: String):Boolean{
        if(binding.a1.text == s && binding.a2.text == s && binding.a3.text == s)
            return true
        if(binding.b1.text == s && binding.b2.text == s && binding.b3.text == s)
            return true
        if(binding.c1.text == s && binding.c2.text == s && binding.c3.text == s)
            return true

        if(binding.a1.text == s && binding.b1.text == s && binding.c1.text == s)
            return true
        if(binding.a2.text == s && binding.b2.text == s && binding.c2.text == s)
            return true
        if(binding.a3.text == s && binding.b3.text == s && binding.c3.text == s)
            return true

        if(binding.a1.text == s && binding.b2.text == s && binding.c3.text == s)
            return true
        if(binding.a3.text == s && binding.b2.text == s && binding.c1.text == s)
            return true

        return false
    }
    private fun result(title: String){
        AlertDialog.Builder(this)
            .setTitle(title)
            .setPositiveButton("reset")
            {_,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }
    private fun resetBoard(){
        for(button in boardList){
            button.text = ""
        }
        if(firstTurn == Turn.CROSS){
            firstTurn = Turn.CIRCLE
        }
        else if(firstTurn == Turn.CIRCLE){
            firstTurn = Turn.CROSS
        }
        currentTurn = firstTurn
        setTurnText()
    }
    private fun fullBoard():Boolean{
        for(button in boardList){
            if(button.text == ""){
                return false
            }
        }
        return true
    }

    private fun addToBoard(button: Button) {
        if(button.text != "")
            return
        if(currentTurn == Turn.CROSS) {
            button.text = "X"
            currentTurn = Turn.CIRCLE
        }
        else if(currentTurn == Turn.CIRCLE){
            button.text = "O"
            currentTurn = Turn.CROSS
        }
        setTurnText()
    }

    private fun setTurnText() {
        if(currentTurn == Turn.CROSS){
            binding.turnTV.text = "Turn X"
        }
        else{
            binding.turnTV.text = "Turn O"
        }
    }
}

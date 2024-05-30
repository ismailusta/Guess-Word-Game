package com.example.guesswordgame

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Arrays
import java.util.Collections
import java.util.Random

class MainActivity : AppCompatActivity() {

    internal var Days = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    lateinit var day: String
    lateinit var random: Random



    lateinit var txtRightAnswer: TextView
    lateinit var txtQuestionContainer : TextView
    lateinit var txtCorrectContainer: TextView
    lateinit var etUserInput: EditText
    lateinit var btShow : Button
    lateinit var btCheck : Button
    lateinit var btNext : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtRightAnswer = findViewById(R.id.txtRightAnswer)
        txtQuestionContainer = findViewById(R.id.txtRandom)
        txtCorrectContainer = findViewById(R.id.textView1)
        etUserInput = findViewById(R.id.etUserInput)
        btShow = findViewById(R.id.btShow)
        btCheck = findViewById(R.id.btCheck)
        btNext = findViewById(R.id.btNext)

        random = Random()
        day = Days[random.nextInt(Days.size)]
        txtQuestionContainer.text = mixWords(day)

        btCheck.setOnClickListener {
            if(etUserInput.text.toString().equals(day, ignoreCase = true)){
                val dialog = Dialog(this@MainActivity)
                dialog.setContentView(R.layout.correct_dialog)
                val bthide = dialog.findViewById<Button>(R.id.btContinue)
                dialog.show()
                bthide.setOnClickListener{
                    dialog.dismiss()
                    day = Days[random.nextInt(Days.size)]
                    txtQuestionContainer.text = mixWords(day)
                    etUserInput.setText("")
                    txtRightAnswer.visibility = View.INVISIBLE
                    txtCorrectContainer.visibility = View.INVISIBLE
                }
            }

            else{
                Toast.makeText(this@MainActivity, "Try Again, you failed", Toast.LENGTH_SHORT).show()
                println("yanlis")
            }
        }
        btNext.setOnClickListener {
            day = Days[random.nextInt(Days.size)]
            txtQuestionContainer.text = mixWords(day)
            etUserInput.setText("")
            txtRightAnswer.visibility = View.INVISIBLE
            txtCorrectContainer.visibility = View.INVISIBLE
        }
        btShow.setOnClickListener {
            txtRightAnswer.visibility = View.VISIBLE
            txtCorrectContainer.visibility = View.VISIBLE
            txtRightAnswer.text = day
        }

        }
        fun mixWords(word:String) : String{
            val word = Arrays.asList<String>(*word.split("".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
            Collections.shuffle(word)
            var mixed=""
            for(i in word){
                mixed+=i
            }
            return mixed
        }
    }

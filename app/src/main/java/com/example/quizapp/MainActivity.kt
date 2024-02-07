package com.example.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questions= arrayOf("Who is the father of Android?",
        "What is the advantage of Kotlin language",
        "Resolution of a picture is accumulation of____on display screen",
        "What does XML stand for?",
        "Select the correct Hex code for Green",
        "What is built-in database of Android studio?",
        "The JDBC thin driver is also called as",
        "In which year the first android was created by Google?",
        "The current Gradle version is_____",
        "If a bean is scoped to HTTP session,scope is"
    )

    private val options= arrayOf(arrayOf("Andrew Lee","Marcian Hoff","James Gosling","Andy Rubin"),
        arrayOf("Interoperability","Coroutines","Both A & B","None of the above"),
        arrayOf("Aspect Ratio","Size","Pixels","Dot Pitch"),
        arrayOf("Extensible Markup Language","Extended Mashup Language","Extensible Mashup Language","X-Markup Language"),
        arrayOf("#FFFFFF","#0066cc","#FFC0CB","#00FF00"),
        arrayOf("MySQL","SQLite","Firebase","MongoDB"),
        arrayOf("Type-4 Driver","Type-2 Driver","Type-1 Driver","Type-3 Driver"),
        arrayOf("2010","2006","2005","2008"),
        arrayOf("8.5","8.7","8.4","8.6"),
        arrayOf("Global-session","Session","Prototype","Request")
    )

    private val correctAnswers= arrayOf(3,2,2,0,3,1,0,3,0,1)

    private var currentQuestionIndex=0
    private var score=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.option1Button.setOnClickListener{
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener{
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener{
            checkAnswer(2)
        }
        binding.option4Button.setOnClickListener{
            checkAnswer(3)
        }
        binding.restartButton.setOnClickListener {
            restartQuiz()
        }
    }
    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
            3 -> binding.option4Button.setBackgroundColor(Color.GREEN)
        }
    }
    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
            3 -> binding.option4Button.setBackgroundColor(Color.RED)
        }
    }
    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(0,0,128))
        binding.option2Button.setBackgroundColor(Color.rgb(0,0,128))
        binding.option3Button.setBackgroundColor(Color.rgb(0,0,128))
        binding.option4Button.setBackgroundColor(Color.rgb(0,0,128))
    }
    private fun showResults(){
        Toast.makeText(this,"Your score is: $score out of ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled=true
    }
    private fun displayQuestion(){
        binding.questionText.text=questions[currentQuestionIndex]
        binding.option1Button.text=options[currentQuestionIndex][0]
        binding.option2Button.text=options[currentQuestionIndex][1]
        binding.option3Button.text=options[currentQuestionIndex][2]
        binding.option4Button.text=options[currentQuestionIndex][3]
        resetButtonColors()
    }
    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex= correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex==correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        } else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex< questions.size - 1){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()},1000)
        } else{
            showResults()
        }
    }
    private fun restartQuiz(){
        currentQuestionIndex=0
        score=0
        displayQuestion()
        binding.restartButton.isEnabled=false
    }
}
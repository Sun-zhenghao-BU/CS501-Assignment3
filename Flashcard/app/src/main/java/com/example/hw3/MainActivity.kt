package com.example.hw3
import android.content.Context
import kotlin.random.Random
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.hw3.databinding.ActivityMainBinding
import android.util.Log

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    var num1=0
    var num2=0
    var operaterNow = ""
    var opNum=0
    var questionsDone=0
    var questionsWellDone=0
    var ifStart=false
    var correctAns=""
    var b_submitIsEnable=false
    var b_submitAlpha = 0.5f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val n1: TextView = findViewById(R.id.number1TextView)
        val n2: TextView = findViewById(R.id.number2TextView)
        val op: TextView = findViewById(R.id.operatorTextView)
        val b_start: Button = findViewById(R.id.StartButton)
        val b_submit: Button = findViewById(R.id.SubmitButton)
        val customerAns: EditText = findViewById(R.id.inputAnsEditText)
        val opList = arrayOf("+","-")


        if (savedInstanceState != null) {
            num1 = savedInstanceState.getInt("num1")
            num2 = savedInstanceState.getInt("num2")
            operaterNow = savedInstanceState.getString("operatorNow", "")
            opNum = savedInstanceState.getInt("opNum")
            questionsDone = savedInstanceState.getInt("questionsDone")
            questionsWellDone = savedInstanceState.getInt("questionsWellDone")
            ifStart = savedInstanceState.getBoolean("ifStart")
            correctAns = savedInstanceState.getString("correctAns", "")
            n1.text=num1.toString()
            n2.text=num2.toString()
            op.text=operaterNow
        }
        b_submit.isEnabled = b_submitIsEnable
        b_submit.alpha = b_submitAlpha

        b_start.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if(!ifStart){
                    ifStart=true
                    b_start.isEnabled = false
                    b_start.alpha = 0.5f
                    num1 = Random.nextInt(1,100)
                    num2 = Random.nextInt(1,20)
                    opNum=Random.nextInt(0,2)
                    operaterNow = opList[opNum]
                    if(operaterNow=="+"){
                        correctAns=(num1+num2).toString()
                    } else {
                        correctAns=(num1-num2).toString()
                    }
                    n1.text=num1.toString()
                    n2.text=num2.toString()
                    op.text=operaterNow
                    customerAns.setText("")
                }
            }
        } as View.OnClickListener)

        b_submit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                b_submit.isEnabled = false
                b_submit.alpha = 0.5f
                if(correctAns==customerAns.text.toString()){
                    questionsDone += 1
                    questionsWellDone += 1
                    Toast.makeText(this@MainActivity, "questionsWellDone：$questionsWellDone"+"questionsDone:$questionsDone", Toast.LENGTH_SHORT).show()
                    Log.d("MyTag", "This is a debug message222222222222222222222222222222")
                } else {
                    questionsDone += 1
                    var ss=customerAns.text.toString()
                    Toast.makeText(this@MainActivity, "questionsWellDone：$questionsWellDone"+"questionsDone:$questionsDone", Toast.LENGTH_SHORT).show()
                    Log.d("MyTag", "This is a debug message33333333333333333333333333")
                }

                if (questionsDone <= 9) {
                    num1 = Random.nextInt(1,100)
                    num2 = Random.nextInt(1,20)
                    opNum=Random.nextInt(0,2)
                    operaterNow = opList[opNum]
                    if(operaterNow=="+"){
                        correctAns=(num1+num2).toString()
                    } else {
                        correctAns=(num1-num2).toString()
                    }
                    n1.text=num1.toString()
                    n2.text=num2.toString()
                    op.text=operaterNow
                    customerAns.setText("")
                } else {
                    n1.text=""
                    n2.text=""
                    op.text=""
                    num1=0
                    num2=0
                    operaterNow = ""
                    opNum=0
                    questionsDone=0
                    questionsWellDone=0
                    correctAns=""
                    ifStart = false
                    b_start.isEnabled = true
                    b_start.alpha = 1.0f
                }

                }
        } as View.OnClickListener)

        customerAns.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(customerAns, InputMethodManager.SHOW_IMPLICIT)
            }
        }


        customerAns.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if(customerAns.text.isEmpty() || !ifStart){
                    b_submit.isEnabled = false
                    b_submit.alpha = 0.5f
                } else {
                    b_submit.isEnabled = true
                    b_submit.alpha = 1.0f
                }
            }
        })
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("num1", num1)
        outState.putInt("num2", num2)
        outState.putString("operatorNow", operaterNow)
        outState.putInt("opNum", opNum)
        outState.putInt("questionsDone", questionsDone)
        outState.putInt("questionsWellDone", questionsWellDone)
        outState.putBoolean("ifStart", ifStart)
        outState.putString("correctAns", correctAns)
        outState.putBoolean("b_submitIsEnable", b_submitIsEnable)
        outState.putFloat("b_submitAlpha", b_submitAlpha)
    }
}


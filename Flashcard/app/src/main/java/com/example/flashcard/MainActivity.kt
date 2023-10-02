package com.example.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.flashcard.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var username : EditText
    lateinit var password: EditText
    lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginButton.setOnClickListener {
            if(binding.username.text.toString() == "user" && binding.password.text.toString() == "123456"){
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
package com.anton.story_of_cats_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anton.story_of_cats_kotlin.databinding.ActivityMainBinding

class EndActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView.text = "Спасибо, что поиграли"
        binding.startButton.text = "Пройти еще раз"
        binding.startButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}
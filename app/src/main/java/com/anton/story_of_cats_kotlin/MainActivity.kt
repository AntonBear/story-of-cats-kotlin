package com.anton.story_of_cats_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anton.story_of_cats_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startButton.setOnClickListener {
           val intent = Intent(this, MenuChangeNameActivity::class.java)
            intent.putExtra(UserConstants.CHOICE, 2)
            startActivity(intent)
       }
    }
}
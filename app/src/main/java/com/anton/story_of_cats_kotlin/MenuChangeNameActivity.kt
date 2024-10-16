package com.anton.story_of_cats_kotlin

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.anton.story_of_cats_kotlin.databinding.ActivityMenuChangeNameBinding

class MenuChangeNameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuChangeNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuChangeNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmButton.setOnClickListener {
            val userName = binding.printYourName.text.toString()
            if (userName.isNotEmpty()) {
                val intent = Intent(this, ControllerActivity::class.java)
                intent.putExtra(UserConstants.NAME, userName)
                intent.putExtra(UserConstants.CHOICE, 3)
                startActivity(intent)
            }
        }
    }
}
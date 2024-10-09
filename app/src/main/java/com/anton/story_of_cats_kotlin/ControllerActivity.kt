package com.anton.story_of_cats_kotlin

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.anton.story_of_cats_kotlin.databinding.ActivityTemplateFormBinding
import kotlinx.serialization.json.*
import models.Novel


class ControllerActivity: AppCompatActivity() {

    private lateinit var binding: ActivityTemplateFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemplateFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userChoice = intent.getIntExtra(UserConstants.CHOICE,0)
        val jsonStr = resources.openRawResource(R.raw.novel).bufferedReader().use { it.readText() }
        val page = Json.decodeFromString<Novel>(jsonStr).pages.find { it.id == userChoice }

        if (page == null) { return }

        var text = page.text
        val name = intent.getStringExtra(UserConstants.NAME)
        name?.let {
            text = text.replace("\$username", it)
        }

        binding.templateFormTextView.text = text

        if (userChoice < 4) {
            binding.imageView3.visibility = View.VISIBLE
        }
        if (userChoice == 14) {
            binding.templateFormFirstSelectChoiceButton.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
            binding.templateFormTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.light_blue))
            binding.templateFormTextView.layoutParams.height = 300
            binding.templateFormTextView.textSize = 40f

            val params = binding.templateFormTextView.layoutParams as ViewGroup.MarginLayoutParams
            val bottomMarginInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                130f, 
                resources.displayMetrics
            ).toInt()
            params.setMargins(0, 0, 0, bottomMarginInPx)
            binding.templateFormTextView.layoutParams = params
            val bottomPaddingInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10f,
                resources.displayMetrics
            ).toInt()
            binding.templateFormTextView.setPadding(
                0,
                0,
                0,
                bottomPaddingInPx
            )
        }

        ImageResources.map[page.backGroundScene]?.let {
            binding.templateFormImageView.setImageResource(
                it
            )
        }


        val buttonViews = with(binding) {
            listOf(
                templateFormFirstSelectChoiceButton,
                templateFormSecondSelectChoiceButton,
                templateFormThirdSelectChoiceButton,
            )
        }

        val buttons = page.buttons
        buttonViews.zip(buttons).forEach {
            it.let { (buttonView, buttonModel) ->
                if (buttonModel.text == null) {
                    buttonView.visibility = View.INVISIBLE
                    return@let
                }

                buttonView.visibility = View.VISIBLE
                buttonView.text = buttonModel.text
                buttonView.setOnClickListener {

                    val intent = when(buttonModel.idOfNextScene) {
                        1 -> Intent(this, MainActivity::class.java)
                        else -> Intent(this, ControllerActivity::class.java).apply {
                            putExtra(UserConstants.CHOICE, buttonModel.idOfNextScene)
                        }

                    }
                    startActivity(intent)
                }
            }
        }
    }
}
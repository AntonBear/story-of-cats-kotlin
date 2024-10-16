package com.anton.story_of_cats_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anton.story_of_cats_kotlin.databinding.ActivityTemplateFormBinding
import models.Button
import models.Page


class ControllerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTemplateFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemplateFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NovelRepository.initialize(this)

        val userChoice = intent.getIntExtra(UserConstants.CHOICE, 0)
        loadPage(userChoice)
    }

    private fun loadPage(userChoice: Int) {
        val page = NovelRepository.fetchPage(userChoice)

        if (page == null) {
            showError("Page is not found")
            return
        }

        displayPageContent(page)
    }

    private fun displayPageContent(page: Page) {
        with(binding) {
            templateFormTextView.text = replaceUsername(page.text)
            imageView3.visibility = if (page.id < 4) View.VISIBLE else View.GONE
            ImageResources.map[page.backGroundScene]?.let { resId ->
                templateFormImageView.setImageResource(resId)
            }
            setupButtons(page.buttons)
        }
    }

    private fun replaceUsername(text: String): String {
        val name = intent.getStringExtra(UserConstants.NAME)
        return name?.let { text.replace("\$username", it) } ?: text
    }

    private fun setupButtons(buttons: ArrayList<Button>) {
        val buttonViews = listOf(
            binding.templateFormFirstSelectChoiceButton,
            binding.templateFormSecondSelectChoiceButton,
            binding.templateFormThirdSelectChoiceButton,
        )

        buttonViews.zip(buttons).forEach { (buttonView, buttonModel) ->
            buttonModel.text?.let {
                buttonView.visibility = View.VISIBLE
                buttonView.text = it
                buttonView.setOnClickListener {
                    handleButtonClick(buttonModel)
                }
            } ?: run {
                buttonView.visibility = View.INVISIBLE
            }
        }
    }

    private fun handleButtonClick(buttonModel: Button) {
        val intent = if (buttonModel.idOfNextScene == 1) {
            Intent(this, EndActivity::class.java)
        } else {
            Intent(this, ControllerActivity::class.java).apply {
                putExtra(UserConstants.CHOICE, buttonModel.idOfNextScene)
            }
        }
        startActivity(intent)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

package com.agening.joke2app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.agening.joke2app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        viewModel = (application as JokeApp).viewModel
        binding.progressBar.visibility = View.INVISIBLE

        binding.actionButton.setOnClickListener {button->
            button.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }
        viewModel.init(object:TextCallback{
            override fun provideText(text: String)=runOnUiThread {
                binding.actionButton.isEnabled = true
                binding.progressBar.visibility = View.INVISIBLE
                binding.textView.text = text
            }

        })

    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }

}
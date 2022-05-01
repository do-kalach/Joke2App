package com.agening.joke2app

import android.app.Application

class JokeApp:Application() {

    lateinit var viewModel:ViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(TestModel())
    }

}
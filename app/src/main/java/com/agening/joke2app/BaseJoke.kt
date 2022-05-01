package com.agening.joke2app

class BaseJoke(private val text: String, private val punchline: String) {

    fun getJokeUi() = "$text\n$punchline"

}
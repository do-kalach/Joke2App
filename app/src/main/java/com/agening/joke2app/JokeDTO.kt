package com.agening.joke2app

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JokeDTO(
    @SerializedName("Title")
    @Expose
    private val title: String,

    @SerializedName("Plot")
    @Expose
    private val plot: String
) {
    fun toJoke() = BaseJoke(title, plot)
}
package com.agening.joke2app

import android.util.Log
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.GET
import java.io.BufferedInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

import java.net.UnknownHostException

class BaseJokeService(){

    companion object{
        const val JOKE_URL = "http://www.omdbapi.com/?i=tt3896198&apikey=38cabe6e"
    }


}


interface JokeService {

    @GET("http://www.omdbapi.com/?i=tt3896198&apikey=38cabe6e")
    fun getJoke(): Call<JokeDTO>

}

interface ServiceCallback{

    fun returnSuccess(data:JokeDTO)

    fun returnError(type:ErrorType)

}

enum class ErrorType{
    NO_CONNECTION,
    OTHER
}
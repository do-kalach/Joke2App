package com.agening.joke2app

import android.util.Log
import com.google.gson.Gson
import java.io.BufferedInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

import java.net.UnknownHostException

class BaseJokeService(private val gson: Gson):JokeService{

    companion object{
        const val JOKE_URL = "http://www.omdbapi.com/?i=tt3896198&apikey=38cabe6e"
    }


    override fun getJoke(callback: ServiceCallback) {
        Thread{
            var connection:HttpURLConnection?=null
            try {
                val url = URL(JOKE_URL)
                connection = url.openConnection() as HttpURLConnection
                Log.d("TAG", "getJoke: ")
                InputStreamReader(BufferedInputStream(connection.inputStream)).use {
                    val line:String = it.readText()
                    val dto = gson.fromJson(line, JokeDTO::class.java)
                    callback.returnSuccess(dto)
                }
            }catch (e:Exception){
                if (e is UnknownHostException)
                    callback.returnError(ErrorType.NO_CONNECTION)
                else
                    callback.returnError(ErrorType.OTHER)
            }finally {
                connection?.disconnect()
            }
        }.start()
    }

}


interface JokeService {

    fun getJoke(callback:ServiceCallback)

}

interface ServiceCallback{

    fun returnSuccess(data:JokeDTO)

    fun returnError(type:ErrorType)

}

enum class ErrorType{
    NO_CONNECTION,
    OTHER
}
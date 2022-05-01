package com.agening.joke2app

class TestModel() : Model<Any, Any> {

    private var callback: ResultCallback<Any, Any>? = null

    private var count = 1

    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            if (count % 2 == 0)
                callback?.provideSuccess("success")
            else
                callback?.provideError("error")
            count++
        }.start()
    }

    override fun init(callback: ResultCallback<Any, Any>) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

}

interface Model<S, E> {

    fun getJoke()

    fun init(callback: ResultCallback<S, E>)

    fun clear()
}

interface ResultCallback<S, E> {

    fun provideSuccess(data: S)

    fun provideError(error: E)
}
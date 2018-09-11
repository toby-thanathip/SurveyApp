package nimbl3.surveyapp.controller

import android.app.Application

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext(): App {
            return instance!!
        }
    }
}
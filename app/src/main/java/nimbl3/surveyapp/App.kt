package nimbl3.surveyapp

import android.app.Application

const val SURVEY_TITLE = "SURVEY_TITLE"
const val AUTHORIZATION = "Authorization"
const val BEARER = "Bearer"
const val AUTH_TOKEN = "authToken"

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
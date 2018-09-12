package nimbl3.surveyapp.db.storage

import android.content.Context.MODE_PRIVATE
import nimbl3.surveyapp.App

object KeyStorage {

    fun saveString(name: String, data: String) {
        val pref = App.applicationContext().getSharedPreferences("my_preferences", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(name,  data)
        editor.apply()
    }

    fun showString(name: String) : String {
        val pref = App.applicationContext().getSharedPreferences("my_preferences", MODE_PRIVATE)
        return pref.getString(name, "")
    }

}
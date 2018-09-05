package nimbl3.surveyapp.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nimbl3.surveyapp.R
import nimbl3.surveyapp.service.SurveyApiService

class SurveyIndexActivity : AppCompatActivity() {

    val apiService = SurveyApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

//       TODO ONLY GENERATE NEW TOKEN IF EXPIRED
//       TODO SENSITIVE DATA
        apiService.getToken("password", "carlos@nimbl3.com", "antikera")
                .flatMap { result ->
                    apiService.getSurveys("Bearer ${result.access_token}", 1,20)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    //TODO ADD DATA TO RECYCLERVIEW
                    Log.d("NIMBL3LOG", "There are ${result.size} surveys")
                }, { error ->
                    Log.e("NIMBL3LOG", error.toString())
                })
    }


}

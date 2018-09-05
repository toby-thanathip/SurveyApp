package nimbl3.surveyapp.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nimbl3.surveyapp.R
import nimbl3.surveyapp.service.SurveyApiService
import nimbl3.surveyapp.view.fragment.viewpager.SurveysPagerAdapter

class SurveyIndexActivity : AppCompatActivity() {

    private lateinit var apiService : SurveyApiService
    private lateinit var pagerAdapter: SurveysPagerAdapter
    private lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        super.onResume()

//       TODO ONLY GENERATE NEW TOKEN IF EXPIRED
//       TODO SENSITIVE DATA
//       TODO USE PICASSO FOR IMAGES

        apiService.getToken("password", "carlos@nimbl3.com", "antikera")
                .flatMap { result ->
                    apiService.getSurveys("Bearer ${result.access_token}", 1,20)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    pagerAdapter = SurveysPagerAdapter(supportFragmentManager, result)
                    viewPager.adapter = pagerAdapter
                    Log.d("NIMBL3LOG", "There are ${result.size} surveys")
                }, { error ->
                    Log.e("NIMBL3LOG", error.toString())
                })
    }

    private fun init() {
        apiService = SurveyApiService.create()
        viewPager = findViewById(R.id.viewPager)
    }


}

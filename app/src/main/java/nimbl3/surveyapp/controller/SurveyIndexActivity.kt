package nimbl3.surveyapp.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.relex.circleindicator.CircleIndicator
import nimbl3.surveyapp.R
import nimbl3.surveyapp.service.SurveyApiService
import nimbl3.surveyapp.view.fragment.viewpager.SurveysPagerAdapter

//       TODO ONLY GENERATE NEW TOKEN IF EXPIRED
//       TODO SENSITIVE DATA
//       TODO MAKE VIEWPAGER INFINITE

class SurveyIndexActivity : AppCompatActivity() {

    private lateinit var apiService : SurveyApiService
    private lateinit var pagerAdapter: SurveysPagerAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var progressBar : ProgressBar
    private lateinit var toolbar : Toolbar
    private lateinit var indicator: CircleIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_index)
        init()
        fetchSurveys()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    private fun init() {
        apiService = SurveyApiService.create()

        toolbar = findViewById(R.id.toolbar)
        viewPager = findViewById(R.id.view_pager)
        progressBar = findViewById(R.id.progressBar)
        indicator = findViewById(R.id.indicator)

        initToolbar()
        initViewPager()
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_refresh)
        toolbar.setNavigationOnClickListener {
            fetchSurveys()
        }
        supportActionBar?.title = ""
    }

    private fun initViewPager() {
        pagerAdapter = SurveysPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        indicator.setViewPager(viewPager)
        pagerAdapter.registerDataSetObserver(indicator.dataSetObserver)
    }

    private fun fetchSurveys() {
        pagerAdapter.clear()
        progressBar.visibility = VISIBLE
        apiService.getToken("password", "carlos@nimbl3.com", "antikera")
                .flatMap { result ->
                    apiService.getSurveys("Bearer ${result.access_token}", 1,20)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    pagerAdapter.refresh(result)
                    progressBar.visibility = INVISIBLE
                }, { error ->
                    Log.e("NIMBL3LOG", error.toString())
                })
    }


}

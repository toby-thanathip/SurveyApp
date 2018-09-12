package nimbl3.surveyapp.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.view.Menu
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nimbl3.surveyapp.R
import nimbl3.surveyapp.model.Survey
import nimbl3.surveyapp.service.RepositoryProvider
import nimbl3.surveyapp.service.SurveyApiService
import nimbl3.surveyapp.view.SurveysPagerAdapter
import nimbl3.surveyapp.widgets.KeyStorage
import kotlinx.android.synthetic.main.activity_survey_index.*

class SurveyIndexActivity : AppCompatActivity() {

    private val apiService = RepositoryProvider.provideRepository()
    private lateinit var pagerAdapter: SurveysPagerAdapter

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
        initToolbar()
        initViewPager()
    }

    private fun initToolbar(){
        setSupportActionBar(toolbar)
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_refresh)
            setNavigationOnClickListener {
                fetchSurveys()
            }
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
        beforeFetch()
        apiService.getSurveys("Bearer ${KeyStorage.showString("authToken")}",1,20)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    afterFetch(result)
                }, { error ->
                    Toast.makeText(this@SurveyIndexActivity, error.toString(), Toast.LENGTH_SHORT).show()
                })
    }

    private fun beforeFetch() {
        SurveyApiService.cancelRequests()
        pagerAdapter.clear()
        progressBar.visibility = VISIBLE
    }

    private fun afterFetch(result : ArrayList<Survey>) {
        pagerAdapter.refresh(result)
        progressBar.visibility = INVISIBLE
    }
}

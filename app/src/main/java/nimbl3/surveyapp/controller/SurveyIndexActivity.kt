package nimbl3.surveyapp.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.relex.circleindicator.CircleIndicator
import nimbl3.surveyapp.R
import nimbl3.surveyapp.service.RepositoryProvider
import nimbl3.surveyapp.view.SurveysPagerAdapter

//       TODO 3 retries..
//       TODO SENSITIVE DATA
//       TODO MAKE VIEWPAGER INFINITE

class SurveyIndexActivity : AppCompatActivity() {

    private val apiService = RepositoryProvider.provideRepository()
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
        toolbar = findViewById(R.id.toolbar)
        viewPager = findViewById(R.id.view_pager)
        progressBar = findViewById(R.id.progressBar)
        indicator = findViewById(R.id.indicator)

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
        pagerAdapter.clear()
        progressBar.visibility = VISIBLE

        apiService.getSurveys("Bearer b86386561c61b764e0c02363f7a585e6f6b7262a42dd0a203a03f211765c362b",1,20)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    pagerAdapter.refresh(result)
                    progressBar.visibility = INVISIBLE
                }, { error ->
                    Toast.makeText(this@SurveyIndexActivity, error.toString(), Toast.LENGTH_SHORT).show()
                })
    }
}

package nimbl3.surveyapp.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_survey.*
import nimbl3.surveyapp.R

class SurveyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        init()
    }

    private fun init() {
        businessName.text = intent.getStringExtra("SURVEY_TITLE")
    }
}

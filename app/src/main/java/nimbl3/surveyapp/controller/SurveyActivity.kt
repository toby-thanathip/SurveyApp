package nimbl3.surveyapp.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import nimbl3.surveyapp.R

class SurveyActivity : AppCompatActivity() {

    private lateinit var businessName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        init()
    }

    private fun init() {
        businessName = findViewById(R.id.business_name)
        businessName.text = intent.getStringExtra("SURVEY_TITLE")
    }
}

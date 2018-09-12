package nimbl3.surveyapp.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import nimbl3.surveyapp.R
import nimbl3.surveyapp.SURVEY_TITLE
import nimbl3.surveyapp.controller.SurveyActivity
import nimbl3.surveyapp.model.*
import nimbl3.surveyapp.widgets.GlideApp

const val TITLE = "title"
const val DESCRIPTION = "description"
const val BACKGROUND = "background"

class SurveyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_survey, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.survey_title).text = arguments!!.getString(TITLE)
        view.findViewById<TextView>(R.id.survey_description).text = arguments!!.getString(DESCRIPTION)
        view.findViewById<Button>(R.id.survey_btn).setOnClickListener {
            val intent = Intent(view.context, SurveyActivity::class.java)
            intent.putExtra(SURVEY_TITLE, arguments!!.getString(TITLE))
            startActivity(intent)
        }

        GlideApp.with(this)
                .load(arguments!!.getString(BACKGROUND))
                .into(view.findViewById(R.id.survey_background))


    }

    companion object {
        fun newInstance(survey: Survey): SurveyFragment {
            val args = Bundle()
            args.putString(TITLE, survey.title)
            args.putString(DESCRIPTION, survey.description)
            args.putString(BACKGROUND, survey.cover_image_url + "l")
            val surveyFragment = SurveyFragment()
            surveyFragment.arguments = args
            return surveyFragment
        }
    }



}
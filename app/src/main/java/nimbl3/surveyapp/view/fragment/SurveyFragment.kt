package nimbl3.surveyapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import nimbl3.surveyapp.R
import nimbl3.surveyapp.model.Survey

class SurveyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_survey, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.survey_title).text = arguments!!.getString("title")
        view.findViewById<TextView>(R.id.survey_description).text = arguments!!.getString("description")

        Glide.with(this)
             .load(arguments!!.getString("background"))
             .into(view.findViewById(R.id.survey_background))
    }

    companion object {
        fun newInstance(survey: Survey): SurveyFragment {
            val args = Bundle()
            args.putString("title", survey.title)
            args.putString("description", survey.description)
            args.putString("background", survey.cover_image_url + "l")
            val surveyFragment = SurveyFragment()
            surveyFragment.arguments = args
            return surveyFragment
        }
    }



}
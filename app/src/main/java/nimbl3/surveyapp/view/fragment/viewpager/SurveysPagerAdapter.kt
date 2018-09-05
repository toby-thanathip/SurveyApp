package nimbl3.surveyapp.view.fragment.viewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import nimbl3.surveyapp.model.Survey
import nimbl3.surveyapp.view.fragment.SurveyFragment

class SurveysPagerAdapter(fragmentManager: FragmentManager, private val surveys: List<Survey>) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return SurveyFragment.newInstance(surveys[position])
    }

    override fun getCount(): Int {
        return surveys.size
    }




}
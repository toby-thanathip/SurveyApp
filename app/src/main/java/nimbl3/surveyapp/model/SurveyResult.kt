package nimbl3.surveyapp.model

data class Survey(val name: String, val description: String, val img_url: String)

data class SurveyResult (val total_count: Int, val incomplete_results: Boolean, val surveys: List<Survey>)
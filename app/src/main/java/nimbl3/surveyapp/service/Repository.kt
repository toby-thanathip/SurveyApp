package nimbl3.surveyapp.service

import io.reactivex.Observable
import nimbl3.surveyapp.model.Survey
import nimbl3.surveyapp.model.Token
import retrofit2.Call

class Repository(private val apiService: SurveyApiService) {
    fun getSurveys(acces_token: String, page: Int, perPage: Int): Observable<ArrayList<Survey>> {
        return apiService.getSurveys(acces_token, page, perPage)
    }

    fun getToken(grant_type: String, username: String, password: String): Call<Token> {
        return apiService.getToken(grant_type, username, password)
    }
}
package nimbl3.surveyapp.model

import io.reactivex.Observable
import nimbl3.surveyapp.service.SurveyApiService
import retrofit2.Call

class Repository(private val apiService: SurveyApiService) {
    fun getSurveys(access_token: String, page: Int, perPage: Int): Observable<ArrayList<Survey>> {
        return apiService.getSurveys(access_token, page, perPage)
    }

    fun getToken(grant_type: String, username: String, password: String): Call<Token> {
        return apiService.getToken(grant_type, username, password)
    }
}
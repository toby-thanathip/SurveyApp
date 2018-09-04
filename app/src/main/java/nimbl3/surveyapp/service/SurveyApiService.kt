package nimbl3.surveyapp.service

import io.reactivex.Observable
import nimbl3.surveyapp.model.SurveyResult
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SurveyApiService {

    @GET("surveys.json")
    fun get(@Query("page") page: Int, @Query("per_page") perPage: Int): Observable<SurveyResult>

    companion object Factory {
        val base_url = "https://nimbl3-survey-api.herokuapp.com/"
        fun create(): SurveyApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(base_url)
                    .build()

            return retrofit.create(SurveyApiService::class.java)
        }
    }
}
package nimbl3.surveyapp.service

import io.reactivex.Observable
import nimbl3.surveyapp.model.Survey
import nimbl3.surveyapp.model.Token
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface SurveyApiService {

    @GET("surveys.json")
    fun getSurveys(@Header("Authorization") access_token: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Observable<ArrayList<Survey>>

    @POST("oauth/token")
    fun getToken(@Query("grant_type") grant_type: String, @Query("username") username: String, @Query("password") password: String) : Call<Token>

    companion object Factory {
        private const val BASE_URL = "https://nimbl3-survey-api.herokuapp.com/"
        fun create(): SurveyApiService {
            val authenticator = TokenAuthenticator()
            val okHttpClient = OkHttpClient.Builder()
            val retrofit = Retrofit.Builder()
                    .client(okHttpClient.authenticator(authenticator).build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(SurveyApiService::class.java)
        }
    }
}
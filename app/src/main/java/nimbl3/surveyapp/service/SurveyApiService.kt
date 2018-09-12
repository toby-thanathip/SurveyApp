package nimbl3.surveyapp.service

import io.reactivex.Observable
import nimbl3.surveyapp.AUTHORIZATION
import nimbl3.surveyapp.model.*
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = "https://nimbl3-survey-api.herokuapp.com/"
const val PER_PAGE = "per_page"
const val PAGE = "page"

const val GRANT_TYPE = "grant_type"
const val USERNAME = "username"
const val PASSWORD = "password"

interface SurveyApiService {

    @GET("surveys.json")
    fun getSurveys(@Header(AUTHORIZATION) access_token: String, @Query(PAGE) page: Int, @Query(PER_PAGE) perPage: Int): Observable<ArrayList<Survey>>

    @POST("oauth/token")
    fun getToken(@Query(GRANT_TYPE) grant_type: String, @Query(USERNAME) username: String, @Query(PASSWORD) password: String) : Call<Token>

    companion object Factory {
        private val dispatcher = Dispatcher()

        fun create(): SurveyApiService {
            val authenticator = TokenAuthenticator()
            val okHttpClient = OkHttpClient.Builder()
            dispatcher.maxRequests = 1
            val retrofit = Retrofit.Builder()
                    .client(okHttpClient.authenticator(authenticator).dispatcher(dispatcher).build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(SurveyApiService::class.java)
        }

        fun cancelRequests(){
            dispatcher.cancelAll()
        }
    }
}
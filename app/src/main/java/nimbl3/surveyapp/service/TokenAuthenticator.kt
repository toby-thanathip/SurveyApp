package nimbl3.surveyapp.service

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {

    override fun authenticate(route: Route?, response: Response?): Request? {

        // Refresh your access_token using a synchronous api request
        apiService.getToken("password", "carlos@nimbl3.com", "antikera").map {
            newAccessToken = it.access_token
        }

        // Add new header to rejected request and retry it
        return response?.request()?.newBuilder()
                ?.header("Authorization", newAccessToken)
                ?.build()

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var apiService : SurveyApiService = SurveyApiService.create()
    private lateinit var newAccessToken : String

//    @Throws(IOException::class)
//    fun authenticate(proxy: Proxy, response: Response): Request {
//
//        // Refresh your access_token using a synchronous api request
//        apiService.getToken("password", "carlos@nimbl3.com", "antikera").map {
//            newAccessToken = it.access_token
//        }
//
//        // Add new header to rejected request and retry it
//        return response.request().newBuilder()
//                .header("Authorization", newAccessToken)
//                .build()
//    }
//
//    @Throws(IOException::class)
//    fun authenticateProxy(proxy: Proxy, response: Response): Request? {
//        // Null indicates no attempt to authenticate.
//        return null
//    }
}
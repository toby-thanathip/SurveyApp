package nimbl3.surveyapp.service

import android.util.Log
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {

    override fun authenticate(route: Route?, response: Response?): Request? {
        if(response?.code() == 401) {
            val apiService = RepositoryProvider.provideRepository()
            val newPostResponse= apiService.getToken("password", "carlos@nimbl3.com", "antikera").execute()
//        return when(newPostResponse.isSuccessful) {
//            true -> response?.request()?.newBuilder()?.header("Authorization", "Bearer ${newPostResponse.body()!!.access_token}")?.build()
//            false -> null
//        }

            if(newPostResponse.isSuccessful) {
                Log.d("FCK", newPostResponse.body()!!.access_token)
                return response.request()?.newBuilder()?.header("Authorization", "Bearer ${newPostResponse.body()!!.access_token}")?.build()
            } else {
                return null
            }
        } else {
            return null
        }

    }
}
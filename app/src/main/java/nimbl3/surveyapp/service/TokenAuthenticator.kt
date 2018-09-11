package nimbl3.surveyapp.service

import nimbl3.surveyapp.widgets.KeyStorage
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response?): Request? {
        val apiService = RepositoryProvider.provideRepository()
        val newPostResponse= apiService.getToken("password", "carlos@nimbl3.com", "antikera").execute()
        if(newPostResponse.isSuccessful) {
            val accessToken = newPostResponse.body()!!.access_token
            KeyStorage.saveString("authToken", accessToken)
            return response?.request()?.newBuilder()?.header("Authorization", accessToken)?.build()
        }
        return null
    }
}

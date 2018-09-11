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
            KeyStorage.saveString("authToken", newPostResponse.body()!!.access_token)
            return response?.request()?.newBuilder()?.header("Authorization", "Bearer ${newPostResponse.body()!!.access_token}")?.build()
        }
        return null
    }
}

package nimbl3.surveyapp.service

import nimbl3.surveyapp.*
import nimbl3.surveyapp.widgets.KeyStorage
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {

    override fun authenticate(route: Route?, response: Response?): Request? {
        val apiService = RepositoryProvider.provideRepository()
        val username = App.applicationContext().getString(R.string.nimbl3_username)
        val password = App.applicationContext().getString(R.string.nimbl3_password)
        val newPostResponse= apiService.getToken(PASSWORD, username, password).execute()
        if(newPostResponse.isSuccessful) {
            val accessToken = newPostResponse.body()!!.access_token
            KeyStorage.saveString(AUTH_TOKEN, accessToken)
            return response?.request()?.newBuilder()?.header(AUTHORIZATION, "$BEARER $accessToken")?.build()
        }
        return null
    }


}

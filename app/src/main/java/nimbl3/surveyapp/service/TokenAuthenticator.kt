package nimbl3.surveyapp.service

import nimbl3.surveyapp.R
import nimbl3.surveyapp.App
import nimbl3.surveyapp.db.storage.KeyStorage
import nimbl3.surveyapp.db.repository.RepositoryProvider
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {

    override fun authenticate(route: Route?, response: Response?): Request? {
        val apiService = RepositoryProvider.provideRepository()
        val username = App.applicationContext().getString(R.string.nimbl3_username)
        val password = App.applicationContext().getString(R.string.nimbl3_password)
        val newPostResponse= apiService.getToken("password", username, password).execute()
        if(newPostResponse.isSuccessful) {
            val accessToken = newPostResponse.body()!!.access_token
            KeyStorage.saveString("authToken", accessToken)
            return response?.request()?.newBuilder()?.header("Authorization", "Bearer $accessToken")?.build()
        }
        return null
    }


}

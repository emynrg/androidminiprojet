package tn.esprit.miniprojet.Retrofit


import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.miniprojet.Views.PREF_LOGIN
import tn.esprit.miniprojet.Views.TOKEN

class OAuthInterceptor(context: Context): Interceptor {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        val token = prefs.getString(TOKEN,"")
        Log.i("token",  token.toString())
        request = request.newBuilder().header("Authorization", "Bearer $token").build()

        return chain.proceed(request)
    }
}

class ApiClient {

companion object{
    val URL ="http://10.0.2.2:9091/"
   // val URL ="http://192.168.105.93:9091/"
    var retrofit: Retrofit? = null

    var retrofitToken: Retrofit? = null
    fun getApiClientWithToken(context: Context): Retrofit? {
        if (retrofitToken == null) {
            val client =  OkHttpClient.Builder()
                .addInterceptor(OAuthInterceptor(context))
                .build()
            retrofitToken = Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitToken
    }
    fun getApiClient(): Retrofit? {
        if (retrofit == null) {
            retrofit =
                    Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

}

}
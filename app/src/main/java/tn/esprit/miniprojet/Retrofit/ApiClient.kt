package tn.esprit.miniprojet.Retrofit


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

companion object{
   // val URL ="http://10.0.2.2:9091/"
    val URL ="http://192.168.24.93:9091/"
    var retrofit: Retrofit? = null
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
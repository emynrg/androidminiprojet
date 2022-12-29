package tn.esprit.miniprojet.Services

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import tn.esprit.miniprojet.Models.Car
import tn.esprit.miniprojet.Models.Contact

interface ContactService {
    @POST("contact/{user2}")
    fun Addcontact(
        @Path ("user2") user2 :String
    ): Call<Contact>
}
package tn.esprit.miniprojet.Services

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import tn.esprit.miniprojet.Models.Car
import tn.esprit.miniprojet.Models.Event

interface EventService {



    @Multipart
    @POST("event/{iduser}")
    fun JoinEvent(
        @Part("EventName") EventName: String,
        @Part("EventDescription") EventDescription: String,
        @Path("iduser") idUser : String,
    ): Call<Event>
}
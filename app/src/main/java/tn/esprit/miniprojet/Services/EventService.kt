package tn.esprit.miniprojet.Services

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import tn.esprit.miniprojet.Models.Car
import tn.esprit.miniprojet.Models.Event
import tn.esprit.miniprojet.Models.Post
import tn.esprit.miniprojet.Models.User

interface EventService {




    @POST("event")
    fun JoinEvent(
        @Body event : Event
    ): Call<Event>

    @GET("event/{iduser}")
    fun geteventbyid(
        @Path ("iduser") iduser :String
    ):Call<MutableList<Event>>

    @DELETE("event/{idEvent}")
    fun deleteEventbyid(
        @Path ("idEvent") idEvent :String
    ):Call<String>





}
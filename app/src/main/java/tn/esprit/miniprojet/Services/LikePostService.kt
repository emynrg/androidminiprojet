package tn.esprit.miniprojet.Services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import tn.esprit.miniprojet.Models.Event
import tn.esprit.miniprojet.Models.LikePost

interface LikePostService {



    @POST("likeP/{postid}")
    fun likePost(
        @Path("postid") postid :String

    ): Call<MutableList<LikePost>>



    @GET("likeP/user")
    fun getLikebyUser():Call<MutableList<LikePost>>



}
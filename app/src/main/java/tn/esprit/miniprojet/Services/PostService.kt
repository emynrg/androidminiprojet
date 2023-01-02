package tn.esprit.miniprojet.Services

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import tn.esprit.miniprojet.Models.Post

interface PostService {
   @Multipart
    @POST("post")
    fun AddPost(
     @Part("titre") titre: RequestBody,
     @Part("description") description: RequestBody,
     @Part imagePost: MultipartBody.Part,
     @Part("user") idUser : String,
                ): Call<Post>

    @GET("post")
    fun getAll(): Call<MutableList<Post>>



}
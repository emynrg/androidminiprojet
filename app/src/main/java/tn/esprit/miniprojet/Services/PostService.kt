package tn.esprit.miniprojet.Services

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import tn.esprit.miniprojet.Models.Post

interface PostService {
    @Multipart
    @POST("post")
    fun AddPost(@Part("titre") marque: RequestBody,
                @Part("description") description: RequestBody,
                @Part imagePost: MultipartBody.Part,
    ): Call<Post>

    @GET("post")
    fun getAll(): Call<MutableList<Post>>



}
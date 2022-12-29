package tn.esprit.miniprojet.Services

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import tn.esprit.miniprojet.Models.Car


interface CarService {
    @Multipart
    @POST("car")
    fun AddCar(@Part("marque") marque: RequestBody,
               @Part("model") model: RequestBody,
               @Part("description") description: RequestBody,
               @Part image: MultipartBody.Part,
               @Part("user") idUser : String,
    ):Call<Car>


    @GET("car")
    fun getcar():Call<MutableList<Car>>


}


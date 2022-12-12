package tn.esprit.miniprojet.Services

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import tn.esprit.miniprojet.Models.Car


interface CarService {
    @Multipart
    @POST("car")
    fun AddCar(@Part("marque") marque: RequestBody,
               @Part("model") model: RequestBody,
               @Part("description") description: RequestBody,
               @Part image: MultipartBody.Part,
    ):Call<Car>

}


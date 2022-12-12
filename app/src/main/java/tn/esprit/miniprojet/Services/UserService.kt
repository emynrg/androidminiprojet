package tn.esprit.miniprojet.Services

import android.text.Editable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import tn.esprit.miniprojet.Models.User
import tn.esprit.miniprojet.Models.loginResponse

interface UserService {

    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") username:String,
              @Field("password") password:String
    ): Call<loginResponse>



    @POST("user")
    fun signUp(
        @Body user:User,

    ):Call<User>
}
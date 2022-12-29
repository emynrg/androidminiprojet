package tn.esprit.miniprojet.Services

import android.text.Editable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import tn.esprit.miniprojet.Models.Car
import tn.esprit.miniprojet.Models.ResponseUser
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


    @GET("user/affichage/{id}")
    fun getuserbyID(
        @Path ("id") id :String
    ):Call<ResponseUser>



    @GET("user/affichage/{idUser}")
    fun getuserbyID2(
        @Path ("idUser") idUser :String
    ):Call<User>

@PUT("user/{idUser}")
fun UpdateProfile(
    @Path("idUser") idUser: String,
    @Body user:User
):Call<User>



}
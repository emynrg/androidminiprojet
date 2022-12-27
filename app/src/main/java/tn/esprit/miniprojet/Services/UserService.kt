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

@Multipart
@PUT("user/{idUser}")
fun UpdateProfile(
    @Path("idUser") idUser: String,
    @Part("username") username:RequestBody,
    @Part("email") email:RequestBody,
    @Part("datedenaissance") datedenaissance:RequestBody,
    @Part("numero") numero:RequestBody,
):Call<String>



}
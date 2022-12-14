package tn.esprit.miniprojet.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*


data class User(
    var _id:String?=null,
    var username: String? = null,
    var email: String? = null,
    var role: String,
    var datedenaissance: String ? = null,
    var numero: String ,
    var password: String? = null,
    var __v:Int=0

)

data class ResponseUser (
    var _id:String?=null,
    var username: String? = null,
    var email: String? = null,
    var role : String ,
    var datedenaissance: String? = null,
    var numero : Int?=0 ,
    var password: String? = null,
    var __v:Int
)




data class Userforpost(
    var _id:String?=null,
    var username: String? = null,


)


data class User1forContact(
    var _id:String?=null,
    var username: String? = null,
    var email : String?= null,
    var numero: String ,


)
data class User2forContact(
    var _id:String?=null,
    var username: String? = null,
    var email : String?= null,
    var numero: String ,


    )


data class loginResponse (var message: String? = null,
                          var user:ResponseUser?=null,
                          var accessToken : String)
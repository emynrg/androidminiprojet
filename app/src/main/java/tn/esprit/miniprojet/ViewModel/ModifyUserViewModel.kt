package tn.esprit.miniprojet.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.miniprojet.Models.Post
import tn.esprit.miniprojet.Models.ResponseUser
import tn.esprit.miniprojet.Models.User
import tn.esprit.miniprojet.Models.loginResponse
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.UserService

class ModifyUserViewModel : ViewModel() {
    var ModifyLiveData: MutableLiveData<User?> = MutableLiveData()
    val _ModifyLiveData : LiveData<User?> = ModifyLiveData

    var UserLiveData: MutableLiveData<User> =MutableLiveData()
    val _UserLiveData : LiveData<User> = UserLiveData


    fun ModifyUser ( id:String ,user :  User){


        val retrofit= ApiClient.getApiClient()!!.create(UserService::class.java)
        val modifyUser=retrofit.UpdateProfile(id,user)
        modifyUser.enqueue(object : Callback<User> {



            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful){
                    ModifyLiveData.postValue(response.body())
                }else{
                    Log.i("test",  response.errorBody()!!.string())

                    ModifyLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                ModifyLiveData.postValue(null)
                Log.i("failure", t.message.toString())
            }
        })
    }
 /********************************* Affichage 2 ***************************************/




 fun getUserbyID2(id:String) {
     val retrofit = ApiClient.getApiClient()!!.create(UserService::class.java)
     val getuser = retrofit.getuserbyID2(id)
     getuser.enqueue(object : Callback<User> {
         override fun onResponse(call: Call<User>, response: Response<User>) {
             if (response.isSuccessful) {
                 UserLiveData.postValue(response.body())
             } else {
                 Log.i("errorBody", response.errorBody()!!.string())

                 UserLiveData.postValue(response.body())
             }

         }

         override fun onFailure(call: Call<User>, t: Throwable) {
             UserLiveData.postValue(null)
             Log.i("failure", t.message.toString())
         }

     })

 }







    }


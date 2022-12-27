package tn.esprit.miniprojet.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.miniprojet.Models.Post
import tn.esprit.miniprojet.Models.loginResponse
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.UserService

class ModifyUserViewModel : ViewModel() {
    var ModifyLiveData: MutableLiveData<String?> = MutableLiveData()
    val _ModifyLiveData : LiveData<String?> = ModifyLiveData


    fun ModifyUser ( id :String , username : RequestBody, email : RequestBody, datedenaissance : RequestBody, numero : RequestBody){


        val retrofit= ApiClient.getApiClient()!!.create(UserService::class.java)
        val ModifyUser=retrofit.UpdateProfile(id,username,email,datedenaissance,numero)
        ModifyUser.enqueue(object : Callback<String> {



            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful){
                    ModifyLiveData.postValue(response.body())
                }else{
                    Log.i("test",  response.errorBody()!!.string())

                    ModifyLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                ModifyLiveData.postValue(null)
                Log.i("failure", t.message.toString())
            }
        })
    }








    }


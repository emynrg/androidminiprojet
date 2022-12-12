package tn.esprit.miniprojet.ViewModel

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.miniprojet.Models.User
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.UserService

class SignUpViewModel : ViewModel() {
    var signUpLiveData: MutableLiveData<User> = MutableLiveData()
    val _signUpLiveData : LiveData<User> = signUpLiveData

    fun signUp(user:User){
        val retrofit= ApiClient.getApiClient()!!.create(UserService::class.java)
        val addUser=retrofit.signUp(user)
        addUser.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    signUpLiveData.postValue(response.body())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    signUpLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                signUpLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })
    }







}
package tn.esprit.miniprojet.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import tn.esprit.miniprojet.Models.loginResponse
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.UserService
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class LoginViewModel : ViewModel() {

        var loginLiveData: MutableLiveData<loginResponse> =MutableLiveData()
        val _loginLiveData : LiveData<loginResponse> = loginLiveData



        fun getLoginObserver(): MutableLiveData<loginResponse> {
                return  loginLiveData
        }
        fun login(username:String,password:String){

                val retrofit= ApiClient.getApiClient()!!.create(UserService::class.java)
                val addUser=retrofit.login(username,password)
                addUser.enqueue(object : Callback<loginResponse> {



                        override fun onResponse(
                                call: Call<loginResponse>,
                                response: Response<loginResponse>
                        ) {
                                if (response.isSuccessful){
                                        loginLiveData.postValue(response.body())
                                }else{
                                        Log.i("test",  response.errorBody()!!.string())

                                        loginLiveData.postValue(null)
                                }
                        }

                        override fun onFailure(call: Call<loginResponse>, t: Throwable) {
                                loginLiveData.postValue(null)
                                Log.i("failure", t.message.toString())                        }
                })
        }

}
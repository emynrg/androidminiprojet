package tn.esprit.miniprojet.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.miniprojet.Models.Car
import tn.esprit.miniprojet.Models.Contact
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.CarService
import tn.esprit.miniprojet.Services.ContactService

class ContactViewModel  : ViewModel() {
    var ContactLiveData: MutableLiveData<Contact> = MutableLiveData()
    val _ContactLiveData : LiveData<Contact> = ContactLiveData

    fun AddContact(user2 : String, context : Context){
        val retrofit= ApiClient.getApiClientWithToken(context)!!.create(ContactService::class.java)
        val addCar=retrofit.Addcontact(user2)
        addCar.enqueue(object : Callback<Contact> {
            override fun onResponse(call: Call<Contact>, response: Response<Contact>) {
                if (response.isSuccessful){
                    ContactLiveData.postValue(response.body())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    ContactLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<Contact>, t: Throwable) {
                ContactLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })
    }






}
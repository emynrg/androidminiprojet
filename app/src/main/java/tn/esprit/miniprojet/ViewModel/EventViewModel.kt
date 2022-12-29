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
import tn.esprit.miniprojet.Models.Event
import tn.esprit.miniprojet.Models.loginResponse
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.CarService
import tn.esprit.miniprojet.Services.EventService
import tn.esprit.miniprojet.Services.UserService

class EventViewModel : ViewModel() {

    var EventLiveData: MutableLiveData<Event> = MutableLiveData()
    val _EventLiveData : LiveData<Event> = EventLiveData
    fun joinEvent(eventname:String,eventDescription:String,iduser : String){

        val retrofit= ApiClient.getApiClient()!!.create(EventService::class.java)
        val addUser=retrofit.JoinEvent(eventname,eventDescription,iduser)
        addUser.enqueue(object : Callback<Event> {



            override fun onResponse(
                call: Call<Event>,
                response: Response<Event>
            ) {
                if (response.isSuccessful){
                    EventLiveData.postValue(response.body())
                }else{
                    Log.i("test",  response.errorBody()!!.string())

                    EventLiveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Event>, t: Throwable) {
                EventLiveData.postValue(null)
                Log.i("failure", t.message.toString())
            }
        })
    }


}
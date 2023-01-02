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
import tn.esprit.miniprojet.Models.User
import tn.esprit.miniprojet.Models.loginResponse
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.CarService
import tn.esprit.miniprojet.Services.EventService
import tn.esprit.miniprojet.Services.UserService

class EventViewModel : ViewModel() {

    var EventLiveData: MutableLiveData<Event> = MutableLiveData()
    val _EventLiveData : LiveData<Event> = EventLiveData
    var EventLiveData1: MutableLiveData<MutableList<Event>> = MutableLiveData()
    val _EventLiveData1 : LiveData<MutableList<Event>> = EventLiveData1



    fun joinEvent(event : Event){

        val retrofit= ApiClient.getApiClient()!!.create(EventService::class.java)
        val addUser=retrofit.JoinEvent(event)
        addUser.enqueue(object : Callback<Event> {
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                if (response.isSuccessful){
                    EventLiveData.postValue(response.body())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    EventLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<Event>, t: Throwable) {
                EventLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })
    }

    /*************************afiichage *****************/
    fun getEventbyID(id:String,context: Context) {

        val retrofit = ApiClient.getApiClientWithToken(context)!!.create(EventService::class.java)
        val getuser = retrofit.geteventbyid(id)
        getuser.enqueue(object : Callback<MutableList<Event>> {
            override fun onResponse(call: Call<MutableList<Event>>, response: Response<MutableList<Event>>) {
                if (response.isSuccessful) {

                    EventLiveData1.postValue(response.body())
                } else {
                    Log.i("errorBody", response.errorBody()!!.string())
                    Log.i("aaaaaaaaaaaaa","aaaaaaaaaaa")
                    EventLiveData1.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<MutableList<Event>>, t: Throwable) {
                EventLiveData1.postValue(null)
                Log.i("failure", t.message.toString())
            }

        })

    }






}
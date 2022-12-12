package tn.esprit.miniprojet.ViewModel

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
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.CarService

class CarViewModel : ViewModel() {
    var CarLiveData: MutableLiveData<Car> = MutableLiveData()
    val _CarLiveData : LiveData<Car> = CarLiveData


    fun AddCar(marque: RequestBody, model: RequestBody, description: RequestBody, image: MultipartBody.Part){
        val retrofit= ApiClient.getApiClient()!!.create(CarService::class.java)
        val addCar=retrofit.AddCar(marque,model,description,image)
        addCar.enqueue(object : Callback<Car> {
            override fun onResponse(call: Call<Car>, response: Response<Car>) {
                if (response.isSuccessful){
                    CarLiveData.postValue(response.body())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    CarLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<Car>, t: Throwable) {
                CarLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })
    }

}
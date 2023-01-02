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
import tn.esprit.miniprojet.Models.Post
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.CarService
import tn.esprit.miniprojet.Services.PostService

class PostViewModel : ViewModel() {
    var PostLiveData: MutableLiveData<Post> = MutableLiveData()
    val _PostLiveData : LiveData<Post> = PostLiveData


    fun AddPost(titre: RequestBody, description: RequestBody, imagePost: MultipartBody.Part , idUser:String, context : Context){
        val retrofit= ApiClient.getApiClientWithToken(context)!!.create(PostService::class.java)
        val addCar=retrofit.AddPost(titre,description,imagePost,idUser )
        addCar.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    PostLiveData.postValue(response.body())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    PostLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                PostLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })
    }










}
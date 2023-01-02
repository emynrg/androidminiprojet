package tn.esprit.miniprojet.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.miniprojet.Models.Post
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.PostService

class listPostViewModel : ViewModel() {

    var postLiveData: MutableLiveData<MutableList<Post>> = MutableLiveData()
    val _postLiveData : LiveData<MutableList<Post>> = postLiveData







    fun getAllPosts() {
        val retrofit = ApiClient.getApiClient()!!.create(PostService::class.java)
        val getPost = retrofit.getAll()
        getPost.enqueue(object : Callback<MutableList<Post>> {
            override fun onResponse(
                call: Call<MutableList<Post>>,
                response: Response<MutableList<Post>>
            ) {
                if (response.isSuccessful) {
                    postLiveData.postValue(response.body())
                } else {
                    Log.i("errorBody", response.errorBody()!!.string())

                    postLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {
                postLiveData.postValue(null)
                Log.i("failure", t.message.toString())
            }

        })


    }
}
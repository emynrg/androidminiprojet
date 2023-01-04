package tn.esprit.miniprojet.ViewModel.Adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.miniprojet.Models.*
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.ContactService
import tn.esprit.miniprojet.Services.LikePostService
import tn.esprit.miniprojet.Services.UserService
import tn.esprit.miniprojet.ViewModel.LoginViewModel
import tn.esprit.miniprojet.ViewModel.ModifyUserViewModel
import tn.esprit.miniprojet.Views.ID
import tn.esprit.miniprojet.Views.PREF_LOGIN
import tn.esprit.miniprojet.Views.USERNAME

class PostAdapter(val context: Context, private val listPost: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var LikeLiveData: MutableLiveData<MutableList<LikePost>> = MutableLiveData()
    val _LikeLiveData : LiveData<MutableList<LikePost>> = LikeLiveData
    var LikeLiveData1: MutableLiveData<MutableList<LikePost>> = MutableLiveData()
    val _LikeLiveData1 : LiveData<MutableList<LikePost>> = LikeLiveData1
    var list =ArrayList<LikePost>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post =listPost[position]
       // Log.i("aaaaaaaaaa",listPost.toString())
        holder.tvtitrePost.setText(post.titre)
        holder.tv_description.setText(post.description)


        if(post.user.username.toString().equals(context.getSharedPreferences(
                PREF_LOGIN,
                AppCompatActivity.MODE_PRIVATE).getString(USERNAME,"")!!)){
            holder.nameowner.setText("Your post")
        }else {
            holder.nameowner.setText(post.user.username)

        }




        holder.likeimage.setOnClickListener {
            holder.likeimage.setImageResource(R.drawable.like)
            Likepost(post._id.toString(),context)

        }





       // holder.imgPost.setImageResource(R.drawable.event3)
        /********************************* ken nheb nrajaa l affichage mel base ***********************************/
        Picasso.get().load(post.imagePost).into(holder.imgPost)



        getLikePost(context)















    }

    override fun getItemCount(): Int {
        return listPost.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgPost: ImageView
        var tvtitrePost: TextView
        var tv_description: TextView
        var nameowner : TextView
        var likeimage : ImageView



        init {
            imgPost=itemView.findViewById(R.id.img_post)
            tvtitrePost = itemView.findViewById(R.id.tv_titre)
            tv_description = itemView.findViewById(R.id.tv_description)
            nameowner = itemView.findViewById(R.id.postOmwner)
            likeimage = itemView.findViewById(R.id.likeimage)

        }
    }


    private fun Likepost(idpost:String , context: Context){


        val retrofit= ApiClient.getApiClientWithToken(context)!!.create(LikePostService::class.java)
        val addCar=retrofit.likePost(idpost)
        addCar.enqueue(object : Callback<MutableList<LikePost>> {
            override fun onResponse(call: Call<MutableList<LikePost>>, response: Response<MutableList<LikePost>>) {
                if (response.isSuccessful){
                    LikeLiveData.postValue(response.body())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    LikeLiveData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<MutableList<LikePost>>, t: Throwable) {
                LikeLiveData.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })


    }



    private fun getLikePost(context: Context) {
        val retrofit= ApiClient.getApiClientWithToken(context)!!.create(LikePostService::class.java)
        val addCar=retrofit.getLikebyUser()



        addCar.enqueue(object : Callback<MutableList<LikePost>> {
            override fun onResponse(call: Call<MutableList<LikePost>>, response: Response<MutableList<LikePost>>) {
                if (response.isSuccessful){
                    LikeLiveData1.postValue(response.body())
                }else{
                    Log.i("errorBody",  response.errorBody()!!.string())

                    LikeLiveData1.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<MutableList<LikePost>>, t: Throwable) {
                LikeLiveData1.postValue(null)
                Log.i("failure",  t.message.toString())
            }

        })

    }


}

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
import tn.esprit.miniprojet.Models.Post
import tn.esprit.miniprojet.Models.ResponseUser
import tn.esprit.miniprojet.Models.User
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.UserService
import tn.esprit.miniprojet.ViewModel.LoginViewModel
import tn.esprit.miniprojet.ViewModel.ModifyUserViewModel
import tn.esprit.miniprojet.Views.ID
import tn.esprit.miniprojet.Views.PREF_LOGIN
import tn.esprit.miniprojet.Views.USERNAME

class PostAdapter(val context: Context, private val listPost: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


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

       // holder.imgPost.setImageResource(R.drawable.event3)
        /********************************* ken nheb nrajaa l affichage mel base ***********************************/
        Picasso.get().load(post.imagePost).into(holder.imgPost)





    }

    override fun getItemCount(): Int {
        return listPost.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgPost: ImageView
        var tvtitrePost: TextView
        var tv_description: TextView
        var nameowner : TextView



        init {
            imgPost=itemView.findViewById(R.id.img_post)
            tvtitrePost = itemView.findViewById(R.id.tv_titre)
            tv_description = itemView.findViewById(R.id.tv_description)
            nameowner = itemView.findViewById(R.id.postOmwner)

        }
    }



}

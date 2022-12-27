package tn.esprit.miniprojet.ViewModel.Adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tn.esprit.miniprojet.Models.Post
import tn.esprit.miniprojet.R

class PostAdapter(val context: Context, private val listPost: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post =listPost[position]
        Log.i("aaaaaaaaaa",listPost.toString())
        holder.tvtitrePost.setText(post.titre)
        holder.tv_description.setText(post.description)
        Picasso.get().load(post.imagePost).into(holder.imgPost)


    }

    override fun getItemCount(): Int {
        return listPost.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgPost: ImageView
        var tvtitrePost: TextView
        var tv_description: TextView


        init {
            imgPost=itemView.findViewById(R.id.img_post)
            tvtitrePost = itemView.findViewById(R.id.tv_titre)
            tv_description = itemView.findViewById(R.id.tv_description)

        }
    }

}

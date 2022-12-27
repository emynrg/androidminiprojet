package tn.esprit.miniprojet.Views

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.miniprojet.R

class ViewPagerAdapter ( private val title: List<String>,private val description: List<String>, private val image: List<Int>) :
    RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemTitle: TextView = itemView.findViewById(R.id.title)
        val itemDescription: TextView = itemView.findViewById(R.id.description)
        val itemImage: ImageView = itemView.findViewById(R.id.image)

        init {
            itemImage.setOnClickListener { v: View ->
                val position = adapterPosition
                Toast.makeText(itemView.context,"You clicked on item #${position + 1}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false))
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDescription.text = description[position]

        holder.itemImage.setImageResource(image[position])



    }

    override fun getItemCount(): Int {
        return title.size

    }
}
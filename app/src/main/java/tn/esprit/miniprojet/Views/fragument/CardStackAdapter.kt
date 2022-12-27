package tn.esprit.miniprojet.Views.fragument

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tn.esprit.miniprojet.Models.Car
import tn.esprit.miniprojet.Models.ItemModel
import tn.esprit.miniprojet.R

class CardStackAdapter() :
    RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {
       private var items = mutableListOf<Car>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.setData(items[position])
        val car = items[position]
        holder.nama.setText(car.marque)
        holder.usia.setText(car.model)
        holder.kota.setText(car.description)
        Picasso.get().load(car.image).into(holder.image)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var nama: TextView //marque
        var usia: TextView // model
        var kota: TextView //description
        /* fun setData(data: ItemModel) {
             Picasso.get()
                 .load(data.getImage())
                 .fit()
                 .centerCrop()
                 .into(image)
             nama.setText(data.getNama())
             usia.setText(data.getUsia())
             kota.setText(data.getKota())
         }*/

        init {
            image = itemView.findViewById(R.id.item_image)
            nama = itemView.findViewById(R.id.item_name)
            usia = itemView.findViewById(R.id.item_age)
            kota = itemView.findViewById(R.id.item_city)
        }
    }
    fun setItems(items: List<Car>) {
        this.items = items.toMutableList()
    }

/*    fun getItems(): List<Car> {
        return items
    }


    init {
        this.items = items
    }*/
}
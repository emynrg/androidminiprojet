package tn.esprit.miniprojet.ViewModel

import androidx.recyclerview.widget.DiffUtil
import tn.esprit.miniprojet.Models.Car
import tn.esprit.miniprojet.Models.ItemModel

class CardStackCallback(old: List<Car>, baru: List<Car>) :
    DiffUtil.Callback() {
    private val old: List<Car>
    private val baru: List<Car>
    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return baru.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].image === baru[newItemPosition].image
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] === baru[newItemPosition]
    }

    init {
        this.old = old
        this.baru = baru
    }
}
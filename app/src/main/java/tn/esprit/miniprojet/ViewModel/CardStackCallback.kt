package tn.esprit.miniprojet.ViewModel

import androidx.recyclerview.widget.DiffUtil
import tn.esprit.miniprojet.Models.ItemModel

class CardStackCallback(old: List<ItemModel>, baru: List<ItemModel>) :
    DiffUtil.Callback() {
    private val old: List<ItemModel>
    private val baru: List<ItemModel>
    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return baru.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].getImage() === baru[newItemPosition].getImage()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] === baru[newItemPosition]
    }

    init {
        this.old = old
        this.baru = baru
    }
}
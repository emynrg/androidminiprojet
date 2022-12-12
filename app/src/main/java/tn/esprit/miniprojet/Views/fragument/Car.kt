package tn.esprit.miniprojet.Views.fragument

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.yuyakaido.android.cardstackview.*
import tn.esprit.miniprojet.Models.ItemModel
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.CardStackCallback



class Car : Fragment() {
    private var manager: CardStackLayoutManager? = null
    private var adapter: CardStackAdapter? = null
    private lateinit var imageSlider : ImageSlider
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_car, container, false)
        init(root)
        return root
    }

    private fun init(root: View) {
        val cardStackView = root.findViewById<CardStackView>(R.id.card_stack_view)

        val imageSlider = root.findViewById<ImageSlider>(R.id.item_image)

        manager = CardStackLayoutManager(context, object : CardStackListener {
            override fun onCardDragging(direction: Direction, ratio: Float) {
                Log.d(TAG, "onCardDragging: d=" + direction.name + " ratio=" + ratio)
            }

            override fun onCardSwiped(direction: Direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager!!.topPosition + " d=" + direction)
                if (direction == Direction.Right) {
                    Toast.makeText(context, "Direction Right", Toast.LENGTH_SHORT).show()
                }
                if (direction == Direction.Top) {
                    Toast.makeText(context, "Direction Top", Toast.LENGTH_SHORT).show()
                }
                if (direction == Direction.Left) {
                    Toast.makeText(context, "Direction Left", Toast.LENGTH_SHORT).show()
                }
                if (direction == Direction.Bottom) {
                    Toast.makeText(context, "Direction Bottom", Toast.LENGTH_SHORT).show()
                }

                // Paginating
                if (manager!!.topPosition == adapter!!.getItemCount() - 5) {
                    paginate()
                }
            }

            override fun onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager!!.topPosition)
            }

            override fun onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager!!.topPosition)
            }

            override fun onCardAppeared(view: View, position: Int) {
                val tv = view.findViewById<TextView>(R.id.item_name)
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.text)
            }

            override fun onCardDisappeared(view: View, position: Int) {
                val tv = view.findViewById<TextView>(R.id.item_name)
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.text)
            }
        })
        manager!!.setStackFrom(StackFrom.None)
        manager!!.setVisibleCount(3)
        manager!!.setTranslationInterval(8.0f)
        manager!!.setScaleInterval(0.95f)
        manager!!.setSwipeThreshold(0.3f)
        manager!!.setMaxDegree(20.0f)
        manager!!.setDirections(Direction.FREEDOM)
        manager!!.setCanScrollHorizontal(true)
        manager!!.setSwipeableMethod(SwipeableMethod.Manual)
        manager!!.setOverlayInterpolator(LinearInterpolator())
        adapter = CardStackAdapter(addList())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator = DefaultItemAnimator()



    }

    private fun paginate() {
        val old: List<ItemModel> = adapter!!.getItems()
        val baru: List<ItemModel?> =  ArrayList<ItemModel?>(addList())    //ArrayList<Any?>(addList())
        val callback = CardStackCallback(old, baru as List<ItemModel>)
        val hasil = DiffUtil.calculateDiff(callback)
        adapter?.setItems(baru)
        hasil.dispatchUpdatesTo(adapter!!)
    }






    private fun addList(): List<ItemModel> {




        val items: MutableList<ItemModel> = ArrayList<ItemModel>()
        items.add(ItemModel(R.drawable.splash,"aaa","25","aaaaaa"))
        items.add(ItemModel(R.drawable.splash, "Marpuah", "20", "Malang"))
        items.add(ItemModel(R.drawable.splash, "Sukijah", "27", "Jonggol"))
        items.add(ItemModel(R.drawable.splash, "Markobar", "19", "Bandung"))
        items.add(ItemModel(R.drawable.splash, "Marmut", "25", "Hutan"))
        items.add(ItemModel(R.drawable.sample1, "Markonah", "24", "Jember"))
        items.add(ItemModel(R.drawable.sample2, "Marpuah", "20", "Malang"))
        items.add(ItemModel(R.drawable.sample3, "Sukijah", "27", "Jonggol"))
        items.add(ItemModel(R.drawable.sample4, "Markobar", "19", "Bandung"))
        items.add(ItemModel(R.drawable.sample5, "Marmut", "25", "Hutan"))




        return items
    }

    companion object {
        private val TAG = Car::class.java.simpleName
    }
}
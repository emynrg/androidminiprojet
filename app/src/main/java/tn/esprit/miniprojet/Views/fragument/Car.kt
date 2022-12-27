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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.yuyakaido.android.cardstackview.*
import tn.esprit.miniprojet.Models.ItemModel
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.CarViewModel
import tn.esprit.miniprojet.ViewModel.CardStackCallback
import tn.esprit.miniprojet.Views.LoginActivity


class Car : Fragment() {
    lateinit var viewModel: CarViewModel
    var items = mutableListOf<tn.esprit.miniprojet.Models.Car>()
    private var manager: CardStackLayoutManager? = null
    private var adapter: CardStackAdapter? = null
    private lateinit var imageSlider: ImageSlider
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
        viewModel = ViewModelProvider(this).get(CarViewModel::class.java)

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
        adapter = CardStackAdapter()
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator = DefaultItemAnimator()
        addList()
        //(context as LoginActivity).startActivity()

    }

    private fun paginate() {
        /*   val old: List<tn.esprit.miniprojet.Models.Car> = adapter!!.getItems()
           val baru: List<tn.esprit.miniprojet.Models.Car?> =  ArrayList<tn.esprit.miniprojet.Models.Car?>(addList())    //ArrayList<Any?>(addList())
           val callback = CardStackCallback(old, baru as List<tn.esprit.miniprojet.Models.Car>)
           val hasil = DiffUtil.calculateDiff(callback)
           adapter?.setItems(baru)
           hasil.dispatchUpdatesTo(adapter!!)*/
    }


    private fun addList() {

        viewModel.getCar()

        viewModel._CarLiveData1.observe(
            viewLifecycleOwner,
            Observer<MutableList<tn.esprit.miniprojet.Models.Car>> {

                if (it.size > 0) {

                    items = it
                    adapter!!.setItems(it)
                    adapter!!.notifyItemInserted(0)


                }
            })


    }

    companion object {
        private val TAG = Car::class.java.simpleName
    }
}
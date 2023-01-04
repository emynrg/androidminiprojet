package tn.esprit.miniprojet.Views.fragument

import android.content.Intent
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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import tn.esprit.miniprojet.Models.Contact
import tn.esprit.miniprojet.Models.ItemModel
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.CarViewModel
import tn.esprit.miniprojet.ViewModel.CardStackCallback
import tn.esprit.miniprojet.ViewModel.ContactViewModel
import tn.esprit.miniprojet.Views.LoginActivity
import java.io.File
import java.io.FileOutputStream


class Car : Fragment() {
    lateinit var viewModel: CarViewModel
    var items = mutableListOf<tn.esprit.miniprojet.Models.Car>()
    private var manager: CardStackLayoutManager? = null
    private var adapter: CardStackAdapter? = null
    private lateinit var imageSlider: ImageSlider
    lateinit var viewModelContact: ContactViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_car, container, false)


        init(root)
        return root
    }

    private fun init(root: View) {
        val cardStackView = root.findViewById<CardStackView>(R.id.card_stack_view)

        viewModel = ViewModelProvider(this).get(CarViewModel::class.java)
        viewModelContact = ViewModelProvider(this).get(ContactViewModel::class.java)


        val imageSlider = root.findViewById<ImageSlider>(R.id.item_image)

        manager = CardStackLayoutManager(context, object : CardStackListener {
            override fun onCardDragging(direction: Direction, ratio: Float) {
                Log.d(TAG, "onCardDragging: d=" + direction.name + " ratio=" + ratio)
            }

            override fun onCardSwiped(direction: Direction) {

                var x = 0
                Log.d(TAG, "onCardSwiped: p=" + manager!!.topPosition + " d=" + direction)
                if (direction == Direction.Right) {

                    viewModel._CarLiveData1.observe(
                        viewLifecycleOwner,
                        Observer<MutableList<tn.esprit.miniprojet.Models.Car>> {

                            addContact(it[0].user!!.toString())
                            it.removeAt(0)



                        })



                    Toast.makeText(context, "Direction Right", Toast.LENGTH_SHORT).show()
                } else if (direction == Direction.Top) {
                    viewModel._CarLiveData1.observe(
                        viewLifecycleOwner,
                        Observer<MutableList<tn.esprit.miniprojet.Models.Car>> {

                            addContact(it[0].user!!.toString())
                            it.removeAt(0)



                        })

                    Toast.makeText(context, "Direction Top", Toast.LENGTH_SHORT).show()
                } else if (direction == Direction.Left) {
                    viewModel._CarLiveData1.value!!.removeAt(0)


                    Toast.makeText(context, "Direction Left", Toast.LENGTH_SHORT).show()
                } else if (direction == Direction.Bottom) {
                    viewModel._CarLiveData1.value!!.removeAt(0)


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
                Log.d(TAG, "onCardAppeared: " + position + ", id aman: " + tv.text)
            }

            override fun onCardDisappeared(view: View, position: Int) {
                val tv = view.findViewById<TextView>(R.id.item_name)
                Log.d(TAG, "onCardAppeared: " + position + ", id: " + tv.text)
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
        adapter = CardStackAdapter(requireContext())
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

        viewModel.getCar(requireContext())

        viewModel._CarLiveData1.observe(
            viewLifecycleOwner,
            Observer<MutableList<tn.esprit.miniprojet.Models.Car>> {

                if (it.size > 0) {
                    for (i in it) {
                        i.user
                    }
                    items = it
                    adapter!!.setItems(it)
                    adapter!!.notifyItemInserted(0)


                }
            })


    }

    private fun addContact(iduser2: String) {

        viewModelContact = ViewModelProvider(this).get(ContactViewModel::class.java)
        viewModelContact.AddContact(iduser2, requireContext())
        viewModelContact._ContactLiveData.observe(this, Observer<Contact> {
            if (it != null) {
                Toast.makeText(requireContext(), "like sended", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(requireContext(), "", Toast.LENGTH_LONG).show()
            }
        })


    }

    companion object {
        private val TAG = Car::class.java.simpleName
    }


}
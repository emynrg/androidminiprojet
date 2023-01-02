package tn.esprit.miniprojet.Views.fragument

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import tn.esprit.miniprojet.Models.Event
import tn.esprit.miniprojet.Models.Post
import tn.esprit.miniprojet.Models.ResponseUser
import tn.esprit.miniprojet.Models.User
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.Adapter.EventAdapteraffichageProfile
import tn.esprit.miniprojet.ViewModel.Adapter.PostAdapter
import tn.esprit.miniprojet.ViewModel.EventViewModel
import tn.esprit.miniprojet.ViewModel.LoginViewModel
import tn.esprit.miniprojet.ViewModel.listPostViewModel
import tn.esprit.miniprojet.Views.*


class Settings : Fragment() {

    lateinit var viewModel: LoginViewModel

    lateinit var logout : ImageView
    lateinit var usernameaffichage : TextView
    lateinit var buttonmodifier: TextView
    lateinit var nombredevent: TextView


    lateinit var rvEvent: RecyclerView;
    lateinit var listevent:MutableList<Event>
    lateinit var viewModelevent: EventViewModel
    lateinit var eventbutton : Button
    lateinit var matchbutton : Button








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v= inflater.inflate(R.layout.fragment_settings, container, false)
        logout = v.findViewById(R.id.logoutButton)
        usernameaffichage =v.findViewById(R.id.UsernameAffichage)
        nombredevent=v.findViewById(R.id.nombredevent)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        viewModelevent= ViewModelProvider(this).get(EventViewModel::class.java)
        rvEvent=v.findViewById(R.id.recycleVieweventProfile)
        listevent= ArrayList()

        buttonmodifier = v.findViewById(R.id.modifierprofile)
        val text = v.findViewById<TextView>(R.id.text123)

        eventbutton = v.findViewById(R.id.button_event)
        matchbutton  = v.findViewById(R.id.button_match)




        eventbutton.setOnClickListener {

Toast.makeText(context,"event",Toast.LENGTH_SHORT).show()

        }


        matchbutton.setOnClickListener {
            text.isVisible
        }






        getEventbyID(requireContext().getSharedPreferences(PREF_LOGIN,AppCompatActivity.MODE_PRIVATE).getString(ID,"")!!)







        logout.setOnClickListener {
            val preferences: SharedPreferences = this.requireActivity().getSharedPreferences(
                PREF_LOGIN, Context.MODE_PRIVATE)
            val editor:SharedPreferences.Editor=preferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
        buttonmodifier.setOnClickListener {
            val intent= Intent(requireContext(),modifyprofile::class.java)
            startActivity(intent)
        }
        getUser(requireContext().getSharedPreferences(PREF_LOGIN,AppCompatActivity.MODE_PRIVATE).getString(ID,"")!!)
        // Inflate the layout for this fragment
        return  v
    }




    private fun getUser(id : String) {
        viewModel.getUserbyID(id,requireContext())
        viewModel._UserLiveData.observe( viewLifecycleOwner  , Observer<ResponseUser>{
               if (it!=null){
                  // Log.i("liste userresponse",it.username.toString())
                   usernameaffichage.text =it.username.toString()
                   //emailaffichage.text = requireContext().getSharedPreferences(PREF_LOGIN,AppCompatActivity.MODE_PRIVATE).getString(
                      // EMAIL,"")!!
               }
        }
        )
    }




    private fun getEventbyID(id : String){
        viewModelevent.getEventbyID(id,requireContext())
        viewModelevent._EventLiveData1.observe(viewLifecycleOwner , Observer<MutableList<Event>> {
            if(it!=null){
                listevent=it
                nombredevent.setText(it.size.toString())
                val layoutManagerServices= LinearLayoutManager(requireContext())
                layoutManagerServices.setOrientation(LinearLayoutManager.VERTICAL);
                rvEvent.layoutManager = layoutManagerServices
                val eventAdapter1 = EventAdapteraffichageProfile(requireContext(),listevent)
                Log.i("la liste des event",it.toString())
                rvEvent.adapter = eventAdapter1
                eventAdapter1.notifyItemChanged(listevent.size+1)
            }else{
            }
        })
    }











}
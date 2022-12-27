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
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tn.esprit.miniprojet.Models.ResponseUser
import tn.esprit.miniprojet.Models.User
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.LoginViewModel
import tn.esprit.miniprojet.Views.*


class Settings : Fragment() {

    lateinit var viewModel: LoginViewModel

    lateinit var logout : ImageView
    lateinit var usernameaffichage : TextView
    lateinit var emailaffichage : TextView
    lateinit var telephoneaffichage : TextView
    lateinit var buttonmodifier: TextView




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
        emailaffichage = v.findViewById(R.id.emailAffichage)
        telephoneaffichage = v.findViewById(R.id.telephoneAffichage)
        buttonmodifier = v.findViewById(R.id.modifierprofile)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


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
                   Log.i("liste userresponse",it.username.toString())

                   usernameaffichage.text =it.username.toString()
                   telephoneaffichage.text =it.numero.toString()
                   emailaffichage.text = requireContext().getSharedPreferences(PREF_LOGIN,AppCompatActivity.MODE_PRIVATE).getString(
                       EMAIL,"")!!


               }
        }


        )




    }












}
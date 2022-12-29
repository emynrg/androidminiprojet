package tn.esprit.miniprojet.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import tn.esprit.miniprojet.Models.Event
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.CarViewModel
import tn.esprit.miniprojet.ViewModel.EventViewModel
import tn.esprit.miniprojet.Views.fragument.Car
import java.io.File
import java.io.FileOutputStream

class joindreEvent : AppCompatActivity() {

    lateinit var description : TextView
    lateinit var name : TextView
    lateinit var Join : Button


    private lateinit var eventViewModel : EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joindre_event)

        Join = findViewById(R.id.joineventButton)
        description = findViewById(R.id.descriptiondetailsTF)
        name = findViewById(R.id.namedetailsTF)

       val  desc = intent.getStringExtra("description")
       val  namee = intent.getStringExtra("name")


        description.text=desc
        name.text=namee
        Join.setOnClickListener {

            JoinEvent(applicationContext.getSharedPreferences(PREF_LOGIN,AppCompatActivity.MODE_PRIVATE).getString(ID,"")!!)
        }






    }


    fun JoinEvent(idUser :String){
        val nameee="testet".toString().trim()
        val descriptionEvent="ajejahjheajhjae".toString().trim()
        Log.i("jaekajajaieaejaajae",name.text.toString())
        eventViewModel= ViewModelProvider(this).get(EventViewModel::class.java)
        eventViewModel.joinEvent(nameee,descriptionEvent,idUser)
        eventViewModel._EventLiveData .observe(this, Observer<Event>{
            if (it!=null){
                Toast.makeText(applicationContext,  "Joined", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(applicationContext,  "FAILED :) ", Toast.LENGTH_LONG).show()
            }
        })
    }


}
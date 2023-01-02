package tn.esprit.miniprojet.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
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
    lateinit var imageEvent : ImageView


    private lateinit var eventViewModel : EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joindre_event)

        Join = findViewById(R.id.joineventButton)
        description = findViewById(R.id.descriptiondetailsTF)
        name = findViewById(R.id.namedetailsTF)
        imageEvent = findViewById(R.id.imageViewEventdetails)

       val  desc = intent.getStringExtra("description")
       val  namee = intent.getStringExtra("name")

        val desc2 = intent.getStringExtra("descriptionaffichageprofile")




        description.text=desc
        name.text=namee

        if(name.text.toString().equals("Event: DRIFT Thursday 1st December")){
            imageEvent.setImageResource(R.drawable.event1)

        }else if(name.text.toString().equals("Event: DRIFT Thursday 2nd December")){
            imageEvent.setImageResource(R.drawable.event2)

        }else if(name.text.toString().equals("Event: DRIFT Thursday 3rd December")) {
            imageEvent.setImageResource(R.drawable.event3)
        }else if(name.text.toString().equals("Event: DRIFT Thursday 4th December")) {
            imageEvent.setImageResource(R.drawable.event4)
        }




        Join.setOnClickListener {

            JoinEvent(desc2.toString())
        }






    }


    fun JoinEvent(x : String){
        val nameee=name.text.toString().trim()
        val descriptionEvent=description.text.toString().trim()
        val event = Event("",nameee,x,applicationContext.getSharedPreferences(PREF_LOGIN,AppCompatActivity.MODE_PRIVATE).getString(ID,"")!!,0)

        eventViewModel= ViewModelProvider(this).get(EventViewModel::class.java)
        eventViewModel.joinEvent(event)
        eventViewModel._EventLiveData .observe(this, Observer<Event>{
            if (it!=null){
                Toast.makeText(applicationContext,  "Joined", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(applicationContext,  "FAILED :) ", Toast.LENGTH_LONG).show()
            }
        })
    }


}
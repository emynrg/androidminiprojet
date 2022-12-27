package tn.esprit.miniprojet.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import tn.esprit.miniprojet.R

class joindreEvent : AppCompatActivity() {

    lateinit var description : TextView
    lateinit var name : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joindre_event)


        description = findViewById(R.id.descriptiondetailsTF)
        name = findViewById(R.id.namedetailsTF)


       val  desc = intent.getStringExtra("description")
       val  namee = intent.getStringExtra("name")



        description.text=desc
        name.text=namee






    }
}
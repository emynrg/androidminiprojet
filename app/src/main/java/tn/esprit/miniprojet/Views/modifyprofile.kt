package tn.esprit.miniprojet.Views

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import tn.esprit.miniprojet.Models.User
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.ModifyUserViewModel

class modifyprofile : AppCompatActivity() {

    lateinit var usernamemodif : EditText
    lateinit var emailmodif : EditText
    lateinit var numbermodif : EditText
    lateinit var datedenaissancemodif : EditText
    lateinit var prefs : SharedPreferences
    lateinit var done : Button
    lateinit var cancel : TextView
    lateinit var viewmodel : ModifyUserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifyprofile)

        viewmodel = ViewModelProvider(this).get(ModifyUserViewModel::class.java)
        usernamemodif = findViewById(R.id.modifierUsernameET)
        emailmodif = findViewById(R.id.modifierEmailET)
        numbermodif = findViewById(R.id.modifiernumberET)
        datedenaissancemodif = findViewById(R.id.modifierdatedenaissanceET)
        prefs = getSharedPreferences(PREF_LOGIN, MODE_PRIVATE)
        done = findViewById(R.id.confirmermodification)



        var id = prefs.getString(ID,"")
        getUserId2(id!!)


        done.setOnClickListener {

            ModifyProfile(id!!)


        }






    }


    private fun getUserId2(id:String){


        viewmodel.getUserbyID2(id)
        viewmodel._UserLiveData.observe(this, Observer<User> {
            if(it!=null){

                usernamemodif.setText(it.username)
                emailmodif.setText(it.email)
                numbermodif.setText(it.numero)
                datedenaissancemodif.setText(it.datedenaissance)

            }else{

            }
        })

    }


    private fun ModifyProfile(id: String) {


        val username= usernamemodif.text.toString().trim()
        val email= emailmodif.text.toString().trim()
        val date = datedenaissancemodif.text.toString().trim()
        val numero = numbermodif.text.toString().trim()
        val user = User(id,username,email,"",date,numero,"",0)
        Log.i("username",username.toString())
        viewmodel.ModifyUser(id,user)
        Toast.makeText(applicationContext,"Update!!",Toast.LENGTH_SHORT).show()
        finish()
    }





}
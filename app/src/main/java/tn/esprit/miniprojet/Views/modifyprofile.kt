package tn.esprit.miniprojet.Views

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
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

        val username = prefs.getString(USERNAME,"")
        usernamemodif.setText(username)

        val email = prefs.getString(EMAIL,"")
        emailmodif.setText(email)

        val number = prefs.getString(NUMERO,"")
        numbermodif.setText(number)

        val date = prefs.getString(DATEDENAISSANCE,"")
        datedenaissancemodif.setText(date)


        var id = prefs.getString(ID,"")

        done.setOnClickListener {

            ModifyProfile(id!!)


        }






    }



    private fun ModifyProfile(id: String) {

        val username= usernamemodif.text.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        val email= emailmodif.text.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        val date = datedenaissancemodif.text.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        val numero = numbermodif.text.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        viewmodel.ModifyUser(id,username,email, date , numero)
        viewmodel._ModifyLiveData.observe(this, androidx.lifecycle.Observer{
            if (it!=null){
                finish()
                //   Toast.makeText(applicationContext,  file.name, Toast.LENGTH_LONG).show()

            }else{
                // Toast.makeText(applicationContext,  file.name, Toast.LENGTH_LONG).show()
            }
        })
    }





}
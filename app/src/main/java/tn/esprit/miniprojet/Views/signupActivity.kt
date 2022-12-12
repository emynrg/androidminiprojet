package tn.esprit.miniprojet.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tn.esprit.miniprojet.Models.User
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.SignUpViewModel

class signupActivity : AppCompatActivity() {

    lateinit var username : EditText
    lateinit var email : EditText
    lateinit var datedenaissance : EditText
    lateinit var numero : EditText
    lateinit var password : EditText
    lateinit var inscri : Button
    lateinit var signUpViewModel: SignUpViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        datedenaissance = findViewById(R.id.datedenaissance)
        numero = findViewById(R.id.numero)
        password = findViewById(R.id.password)
        inscri = findViewById(R.id.button)



        inscri.setOnClickListener{

            if (validationFullname()&& validationEmail()&&validationPhoneNumber()&&validationPassword()){
                SignUp()

            }

        }

    }



    fun validationFullname(): Boolean {
        val usename = username.text.toString().trim()

        if (usename.isEmpty()) {

            Toast.makeText(this, "obligatoire username", Toast.LENGTH_SHORT).show()
            return false

        }else
            return true
        }
    private fun validationEmail(): Boolean {
        val email = email.text.toString().trim()
        if(email.isEmpty()) {
            Toast.makeText(this, "obligatoire ", Toast.LENGTH_SHORT).show()
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "verifier votre adresse", Toast.LENGTH_SHORT).show()
            return false
        } else {

            return true
        }}
    private fun validationPassword(): Boolean {
        val password = password.text.toString().trim ()
        if(password.isEmpty()) {
            Toast.makeText(this, "obligatoire ", Toast.LENGTH_SHORT).show()
            return false
        } else {

            return true
        }}
    private fun validationPhoneNumber(): Boolean {
        val phoneNumber = numero.text.toString().trim ()
        if(phoneNumber.isEmpty()) {
            Toast.makeText(this, "obligatoire ", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }}




    private  fun SignUp(){
        val user = User("", username.text.toString().trim(),email.text.toString().trim(),"user",datedenaissance.text.toString(),numero.text.toString().trim(), password.text.toString().trim(),0)

        signUpViewModel= ViewModelProvider(this).get(SignUpViewModel::class.java)
        signUpViewModel.signUp(user)
        signUpViewModel._signUpLiveData.observe(this, Observer<User>{
            if (it!=null){
                Toast.makeText(applicationContext, "Welcome !", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(applicationContext, " failed !", Toast.LENGTH_LONG).show()
            }
        })
    }






}
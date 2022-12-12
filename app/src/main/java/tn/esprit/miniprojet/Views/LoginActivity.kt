package tn.esprit.miniprojet.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tn.esprit.miniprojet.Models.loginResponse
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {


    lateinit var usernameLogin : EditText
    lateinit var passwordLogin :EditText
    lateinit var loginbtn : Button
    lateinit var loginViewModel : LoginViewModel
    lateinit var signUp : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        usernameLogin = findViewById(R.id.username)
        passwordLogin = findViewById(R.id.password)
        loginbtn = findViewById(R.id.button)
        signUp= findViewById(R.id.SignUP)


signUp.setOnClickListener(){

    startActivity(Intent(this,signupActivity::class.java) )
}


        loginbtn.setOnClickListener{
            if (validationUsername()&&validationPassword()){
                login()

                passwordLogin.setText("");


            }
            passwordLogin.setText("");



        }



    }
    fun validationUsername(): Boolean {
        val username = usernameLogin.text.toString().trim()

        if (username.isEmpty()) {

            Toast.makeText(applicationContext, "champ obligatoire", Toast.LENGTH_SHORT).show()
            return false

        }else {
            return true
        }



    }
    fun validationPassword(): Boolean {
        val password = passwordLogin.text.toString().trim ()
        if(password.isEmpty()) {
            Toast.makeText(applicationContext, "champ obligatoire", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }}

    private  fun login()  {
        loginViewModel= ViewModelProvider(this).get(LoginViewModel::class.java)
            loginViewModel.login(usernameLogin.text.toString().trim(),passwordLogin.text.toString().trim())
            loginViewModel._loginLiveData.observe(this, Observer<loginResponse>{
            if (it!=null){
                Toast.makeText(applicationContext, "Login succes !", Toast.LENGTH_LONG).show()

                startActivity(Intent(this,Home::class.java) )


            }else{
                Toast.makeText(applicationContext, "User Not Found !", Toast.LENGTH_LONG).show()

            }
        })

    }




}
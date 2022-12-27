package tn.esprit.miniprojet.Views

import android.content.Intent
import android.content.SharedPreferences
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




const val ID="id"
const val USERNAME="username"
const val EMAIL="email"
const val PASSWORD="password"
const val TOKEN="token"
const val DATEDENAISSANCE="datedenaissance"
const val NUMERO="numero"
const val ROLE="role"
const val USER="user"
const val PREF_LOGIN="Remember me"






class LoginActivity : AppCompatActivity() {


    lateinit var usernameLogin : EditText
    lateinit var passwordLogin :EditText
    lateinit var loginbtn : Button
    lateinit var loginViewModel : LoginViewModel
    lateinit var signUp : Button
    lateinit var prefs : SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        prefs = getSharedPreferences(PREF_LOGIN, MODE_PRIVATE)

        usernameLogin = findViewById(R.id.username)
        passwordLogin = findViewById(R.id.password)
        loginbtn = findViewById(R.id.button)
        signUp= findViewById(R.id.SignUP)


signUp.setOnClickListener(){

    startActivity(Intent(this,signupActivity::class.java) )
}




        if (prefs.getString(USERNAME,"")!!.isNotEmpty() and prefs.getString(PASSWORD,"")!!.isNotEmpty()){
            val intent = Intent(applicationContext,Home::class.java)
            startActivity(intent)
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

                prefs.edit().apply(){


                    putString(ID,it.user?._id)
                    putString(USERNAME,it.user?.username)
                    putString(EMAIL,it.user?.email)
                    putString(PASSWORD,it.user?.password)
                    putString(NUMERO,it.user?.numero.toString())
                    putString(DATEDENAISSANCE,it.user?.datedenaissance)
                    putString(TOKEN,it.accessToken)
                    putString(ROLE,it.user?.role)
                    apply()




                }

                Toast.makeText(applicationContext, "Login succes !", Toast.LENGTH_LONG).show()

                startActivity(Intent(this,Home::class.java) )


            }else{
                Toast.makeText(applicationContext, "User Not Found !", Toast.LENGTH_LONG).show()

            }
        })

    }




}
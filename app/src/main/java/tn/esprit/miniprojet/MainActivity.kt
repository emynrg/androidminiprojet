package tn.esprit.miniprojet

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import tn.esprit.miniprojet.Views.LoginActivity
import tn.esprit.miniprojet.Views.welcomePage

@SuppressLint("CustomSplashScreen")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler(mainLooper).postDelayed({ //This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(this@MainActivity, welcomePage::class.java)
            startActivity(i)
            // close this activity
            finish()
        }, 3500)
    }
}




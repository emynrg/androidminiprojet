package tn.esprit.miniprojet.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.denzcoskun.imageslider.adapters.ViewPagerAdapter
import me.relex.circleindicator.CircleIndicator3
import tn.esprit.miniprojet.R

class welcomePage : AppCompatActivity() {



    lateinit var view_pager2 : ViewPager2
    lateinit var login : Button
    lateinit var signin : Button

    private var imagesList = mutableListOf<Int>()
    private var titlesList = mutableListOf<String>()
    private var titlesList2 = mutableListOf<String>()







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)
        login = findViewById(R.id.login)
        signin= findViewById(R.id.signin)

        postToList()


        view_pager2 = findViewById<ViewPager2>(R.id.view_pager2)
        view_pager2.adapter = ViewPagerAdapter(titlesList,titlesList2,imagesList)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val indicator = findViewById<CircleIndicator3>(R.id.ind)
        indicator.setViewPager(view_pager2)


        login.setOnClickListener(){

            startActivity(Intent(this,LoginActivity::class.java) )
        }
        signin.setOnClickListener(){

            startActivity(Intent(this,signupActivity::class.java) )
        }






    }
    private fun addToList(title: String, description : String , image: Int) {
        titlesList2.add(description)
        titlesList.add(title)
        imagesList.add(image)
    }
    private fun postToList(){

        addToList("Events","Millions of events around TUNISIA ",R.drawable.welcome2)
        addToList("Vehicules","Add your beast pictures",R.drawable.welcome1)
        addToList("Forum","Like and Share your ideas in our forum",R.drawable.welcome3)

    }
}
package tn.esprit.miniprojet.ViewModel.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.miniprojet.Models.Contactaffichage
import tn.esprit.miniprojet.Models.Event
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.Views.ID
import tn.esprit.miniprojet.Views.PREF_LOGIN

class contactAffichageAdapter(val context: Context,private val listcontactaffichage : List<Contactaffichage>) : RecyclerView.Adapter<contactAffichageAdapter.ContactaffichageViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactaffichageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemmatch , parent,false)
        return ContactaffichageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactaffichageViewHolder, position: Int) {
        val contacts = listcontactaffichage[position]
        if(contacts.user1._id.toString().equals(context.getSharedPreferences(
                PREF_LOGIN,
                AppCompatActivity.MODE_PRIVATE).getString(ID,"")!!))
        {
            holder.usernamematch.setText(contacts.user2.username)
            holder.phonenumbermatch.setText(contacts.user2.numero)
            holder.emailmatch.setText(contacts.user2.email)

        }else{
            holder.usernamematch.setText(contacts.user1.username)
            holder.phonenumbermatch.setText(contacts.user1.numero)
            holder.emailmatch.setText(contacts.user1.email)
        }

    }

    override fun getItemCount(): Int {
        return listcontactaffichage.size
    }
    class ContactaffichageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var usernamematch:TextView
            var phonenumbermatch:TextView
            var emailmatch:TextView

        init {
            usernamematch  = itemView.findViewById(R.id.usernamematch)
            phonenumbermatch= itemView.findViewById(R.id.phonenumbermatch)
            emailmatch = itemView.findViewById(R.id.emailmatch)

        }

    }
}
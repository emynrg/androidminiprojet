package tn.esprit.miniprojet.ViewModel.Adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.miniprojet.Models.Event
import tn.esprit.miniprojet.Models.Post
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.Retrofit.ApiClient
import tn.esprit.miniprojet.Services.EventService

class EventAdapteraffichageProfile(val context : Context , private val listEvent : List<Event>) : RecyclerView.Adapter<EventAdapteraffichageProfile.EventViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event , parent,false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = listEvent[position]
        Log.i("listedeseventsamine", listEvent.toString())
        holder.nameEvent.setText(event.EventName)
        holder.DescriptionEvent.setText(event.EventDescription)
        holder.delete.setOnClickListener {
           alerteDialog(event._id!!)
            notifyItemRemoved(position)





        }

    }

    private fun alerteDialog(idevent: String) {
        val builder : AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage("ARE YOU SURE TO LEAVE THIS EVENT ?")
            .setCancelable(false).setPositiveButton("Yes"){

                dialog,id -> deleteEventById(idevent)

            }
            .setNegativeButton("No"){ dialog , id ->
                dialog.cancel()
            }
        val alert = builder.create()
        alert.setTitle("Leave Event")
        alert.show()



    }

    private fun deleteEventById(idevent: String) {

        val retrofit = ApiClient.getApiClient()!!.create(EventService::class.java)
        val delete=retrofit.deleteEventbyid(idevent)
        delete.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful)
                {
                        Log.i("ok" , "ok")
                }else{
                    Log.i("EROORBody" , response.errorBody()!!.string())

                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("failure", t.message.toString())

            }

        })

    }

    override fun getItemCount(): Int {
        return listEvent.size
    }
    class EventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var nameEvent : TextView
        var DescriptionEvent : TextView

        var delete : TextView


        init {
            nameEvent = itemView.findViewById(R.id.eventNameProfile)
            DescriptionEvent=itemView.findViewById(R.id.eventDescriptionProfile)
            delete = itemView.findViewById(R.id.deleteEvent)
        }


    }

}
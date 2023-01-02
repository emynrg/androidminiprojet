package tn.esprit.miniprojet.ViewModel.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.Views.Home
import tn.esprit.miniprojet.Views.joindreEvent


class RecycleEventAdapter (val context: Context) :  RecyclerView.Adapter<RecycleEventAdapter.ViewHolder>()  {

   private var eventsnames = arrayOf("Event: DRIFT Thursday 1st December","Event: DRIFT Thursday 2nd December","Event: DRIFT Thursday 3rd December","Event: DRIFT Thursday 4th December")
   private var eventDescription  = arrayOf("Only Drifting Cars","Only Muscle Cars","Only omar Cars","Only youssef Cars")
   private var eventDescriptiondetails  = arrayOf("Only Drifting Cars Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser"
       ,"Only Muscle CarsLorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser"
       ,"Only omar Cars Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser",
       "Only youssef Cars Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser")

   private var eventImages =  intArrayOf(R.drawable.event1,R.drawable.event2,R.drawable.event3,R.drawable.event4)




    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecycleEventAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.event,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecycleEventAdapter.ViewHolder, position: Int) {
        holder.Eventname.text= eventsnames[position]
        holder.eventDescription.text = eventDescription[position]
        holder.eventImage.setImageResource(eventImages[position])

        var x = eventDescriptiondetails[position]



        holder.itemView.setOnClickListener {
            val intent= Intent(context, joindreEvent::class.java)
            var name = holder.Eventname.text.toString()
            var desc = x
            val desc2 = holder.eventDescription.text.toString()

            intent.putExtra("description",desc)
            intent.putExtra("name",name)
            intent.putExtra("descriptionaffichageprofile",desc2)

            context.startActivity(intent)
        }



    }

    override fun getItemCount(): Int {
        return eventsnames.size
    }


    inner class ViewHolder( itemView : View) : RecyclerView.ViewHolder(itemView){

        var eventImage : ImageView
        var Eventname : TextView
        var eventDescription : TextView
        init {
            eventImage = itemView.findViewById(R.id.eventImage)
            Eventname = itemView.findViewById(R.id.Eventname)
            eventDescription = itemView.findViewById(R.id.eventdescription)
           // (context as LoginActivity).startActivity()



            itemView.setOnClickListener{


               // mcon?.startActivity( Intent(mcon,joindreEvent::class.java))

               /* val position : Int = adapterPosition
                    Toast.makeText(itemView.context,"you clicked on ${eventsnames[position]} ", Toast.LENGTH_LONG).show()

                */
                //startActivity(Intent( it.context,signupActivity::class.java ))



            }


        }

    }

}
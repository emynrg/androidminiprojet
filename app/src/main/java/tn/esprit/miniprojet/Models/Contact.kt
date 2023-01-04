package tn.esprit.miniprojet.Models

data class Contact (
   var message : String
)



data class Contactaffichage(
   var _id:String,
   var user1: User1forContact,
   var user2 :User2forContact,
)

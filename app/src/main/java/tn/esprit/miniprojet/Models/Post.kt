package tn.esprit.miniprojet.Models

data class Post (
    var _id:String?=null,
    var titre:String?=null,
    var description :String?=null,
    var imagePost:String?=null,
    var user:Userforpost,
    var __v:Int=0

)
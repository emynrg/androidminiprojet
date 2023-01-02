package tn.esprit.miniprojet.Models

class ItemModel {
    private var image = 0
     private var nama : String?=null
    private var usia : String?=null
     private  var kota:String? = null



    constructor (image: Int, nama: String?, usia: String, kota: String) {
        this.image = image
        this.nama = nama
        this.usia = usia
        this.kota = kota
    }

    fun ItemModel() {}



    fun getImage(): Int {
        return image
    }

    fun getNama(): String? {
        return nama
    }

    fun getUsia(): String? {
        return usia
    }

    fun getKota(): String? {
        return kota
    }

}
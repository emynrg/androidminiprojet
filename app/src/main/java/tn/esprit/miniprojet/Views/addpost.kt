package tn.esprit.miniprojet.Views

import android.Manifest
import android.app.AlertDialog
import android.content.ClipDescription
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import tn.esprit.miniprojet.Models.Car
import tn.esprit.miniprojet.Models.Post
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.CarViewModel
import tn.esprit.miniprojet.ViewModel.PostViewModel
import java.io.File
import java.io.FileOutputStream

class addpost : AppCompatActivity() {



    private val IMAGE_GALLERY_REQUEST_CODE: Int = 2001
    private val STORAGE_PERMISSION_CODE = 1
    private  lateinit var imgUri: Uri

    lateinit var imagePost : ImageView

    lateinit var titrePost : TextInputEditText
    lateinit var descriptionPost: EditText
    lateinit var addPost : Button
    lateinit var cancel : Button
    private lateinit var postViewModel : PostViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addpost)


        imagePost = findViewById(R.id.addimagePost)

        titrePost = findViewById(R.id.postTitre)
        descriptionPost = findViewById(R.id.postDescription)
        addPost = findViewById(R.id.publishButton)
        cancel = findViewById(R.id.cancel_button)



        cancel.setOnClickListener {
            finish()
        }

        addPost.setOnClickListener {
            if(titrePost.text.toString().equals("")&&descriptionPost.text.toString().equals("")){
                Toast.makeText(this, "Title or Description IS REQUIRED", Toast.LENGTH_SHORT).show()

            }
            AddPost(applicationContext.getSharedPreferences(PREF_LOGIN,AppCompatActivity.MODE_PRIVATE).getString(ID,"")!!)


        }

        imagePost.setOnClickListener {
            if (ContextCompat.checkSelfPermission(applicationContext,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                requestStoragePermission();
            }
        }






    }




    private fun openGallery() {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI).apply {
            type="image/*"
            startActivityForResult(this,IMAGE_GALLERY_REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == IMAGE_GALLERY_REQUEST_CODE) {
            if (data != null && data.data != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    imgUri= data.data!!

                    imagePost.setImageURI(imgUri)

                }
            }
        }
    }

    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            AlertDialog.Builder(this)
                .setTitle("Autorisation nécessaire")
                .setMessage("Cette autorisation est nécessaire pour ajouter une image")
                .setPositiveButton("D'accord",
                    DialogInterface.OnClickListener { dialog, which ->
                        ActivityCompat.requestPermissions(
                            this@addpost,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            STORAGE_PERMISSION_CODE
                        )
                    })
                .setNegativeButton("Annuler",
                    DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                .create().show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_CODE
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
                Toast.makeText(this, "Permission accordée", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun AddPost(idUser :String){

        val fileDir=applicationContext.filesDir
        val file= File(fileDir,"image.jpg")
        val inputStream=contentResolver!!.openInputStream(imgUri)
        val outputStream= FileOutputStream(file)
        inputStream!!.copyTo(outputStream)
        val requestBody=file.asRequestBody("image/*".toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("imagePost", file.name,requestBody)



        val titre=titrePost.text.toString().trim().toRequestBody("plain/text".toMediaTypeOrNull())
        Log.i("model mtaaa l car ", titre.toString())

        val desription=descriptionPost.text.toString().trim().toRequestBody("plain/text".toMediaTypeOrNull())


        postViewModel= ViewModelProvider(this).get(PostViewModel::class.java)
        postViewModel.AddPost(titre,desription,image,idUser,this)
        postViewModel._PostLiveData .observe(this, Observer<Post>{
            if (it!=null){
                Toast.makeText(applicationContext,  "Post added", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(applicationContext, " no", Toast.LENGTH_LONG).show()
            }
        })
    }



}
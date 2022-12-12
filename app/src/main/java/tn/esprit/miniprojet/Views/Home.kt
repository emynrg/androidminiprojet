package tn.esprit.miniprojet.Views


import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputEditText
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import tn.esprit.miniprojet.Models.Car
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.CarViewModel
import tn.esprit.miniprojet.ViewModel.SignUpViewModel
import tn.esprit.miniprojet.Views.fragument.HomeF
import tn.esprit.miniprojet.Views.fragument.Profile
import tn.esprit.miniprojet.Views.fragument.Settings
import java.io.File
import java.io.FileOutputStream

//import tn.esprit.miniprojet.databinding.ActivityHomeBinding


class Home : AppCompatActivity() {
    //private lateinit var binding: ActivityHomeBinding
       private val IMAGE_GALLERY_REQUEST_CODE: Int = 2001
        private val STORAGE_PERMISSION_CODE = 1
        private  lateinit var imgUri: Uri
    private val CAMERA_REQUEST_CODE = 1
    private val GALLERY_REQUEST_CODE = 2
    private lateinit var bitmap : Bitmap


    lateinit var navController: NavController
    lateinit var add : ImageView
    lateinit var carimage : ImageView
    lateinit var saveCar : Button

    lateinit var marque : EditText
    lateinit var modelET : EditText
    lateinit var descriptionET : EditText
     private lateinit var carViewModel : CarViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frame_layout) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)


        add = findViewById(R.id.add)


        add.setOnClickListener {


            showDialog()









        }


    }


    private fun showDialog() {




        val dialog = Dialog(this)



        val options = arrayOf("BMW","Renault","Mazda","Peugoet")
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.addcar)
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        /****************** declaration **********************/

        marque = dialog.findViewById(R.id.marque)
        saveCar= dialog.findViewById(R.id.saveCar)
        modelET = dialog.findViewById<TextInputEditText>(R.id.model)
        descriptionET = dialog.findViewById<TextInputEditText>(R.id.descriptionCar)
        carimage = dialog.findViewById(R.id.imageCar)






        /***************************************************/













        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.FILL_VERTICAL)

/*

        option.adapter =ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,options)



        option.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext, "${options.get(position)}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "please Select an Option", Toast.LENGTH_SHORT).show()
            }


        }
*/
        /*
/***************** bazdeh **********/
        //when you click on the image
        carimage.setOnClickListener {
            val pictureDialog = AlertDialog.Builder(this)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItem = arrayOf("Select photo from Gallery",
                "Capture photo from Camera")
            pictureDialog.setItems(pictureDialogItem) { dialog, which ->

                when (which) {
                    0 -> gallery()
                    1 -> camera()
                }
            }

            pictureDialog.show()
        }
/******************************************/
*/

        carimage.setOnClickListener {
            if (ContextCompat.checkSelfPermission(applicationContext,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                requestStoragePermission();
            }

        }



        saveCar.setOnClickListener{
            AddCar()


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

                    carimage.setImageURI(imgUri)

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
                            this@Home,
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



 /*
    /***************** bazdeh *****************/


    private fun galleryCheckPermission() {

        Dexter.withContext(this).withPermission(
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                gallery()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(
                    this@Home,
                    "You have denied the storage permission to select image",
                    Toast.LENGTH_SHORT
                ).show()
                showRotationalDialogForPermission()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?, p1: PermissionToken?) {
                showRotationalDialogForPermission()
            }
        }).onSameThread().check()
    }

    private fun gallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }


    private fun cameraCheckPermission() {

        Dexter.withContext(this)
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA).withListener(

                object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        report?.let {

                            if (report.areAllPermissionsGranted()) {
                                camera()
                            }

                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?) {
                        showRotationalDialogForPermission()
                    }

                }
            ).onSameThread().check()
    }


    private fun camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            when (requestCode) {

                CAMERA_REQUEST_CODE -> {

                     bitmap = data?.extras?.get("data") as Bitmap

                    //we are using coroutine image loader (coil)
                    carimage.load(bitmap) {
                        crossfade(true)
                        crossfade(1000)
                        transformations(CircleCropTransformation())
                    }
                }

                GALLERY_REQUEST_CODE -> {

                    carimage.load(data?.data) {
                        crossfade(true)
                        crossfade(1000)
                        transformations(CircleCropTransformation())
                    }

                }
            }

        }

    }


    private fun showRotationalDialogForPermission()  {
        AlertDialog.Builder(this)
            .setMessage("It looks like you have turned off permissions"
                    + "required for this feature. It can be enable under App settings!!!")

            .setPositiveButton("Go TO SETTINGS") { _, _ ->

                try {
                    val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)

                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }

            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }.show()


    }





    /*******************************************/


  */
  */
    fun AddCar(){

        val fileDir=applicationContext.filesDir
        val file= File(fileDir,"image.jpg")
        val inputStream=contentResolver.openInputStream(imgUri)
        val outputStream= FileOutputStream(file)
        inputStream!!.copyTo(outputStream)
        val requestBody=file.asRequestBody("image/*".toMediaTypeOrNull())
         val image = MultipartBody.Part.createFormData("image", file.name,requestBody)


        // val file: File = File(imgUri.path!!)
        // val requestFile =RequestBody.create("multipart/form-data".toMediaTypeOrNull(),file)
        // val body = MultipartBody.Part.createFormData("image", file.name, requestFile)
        //val option= option.ge.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        val model=modelET.text.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        val marque=marque.text.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        val desription=descriptionET.text.toString().trim().toRequestBody("text/plain".toMediaTypeOrNull())
        carViewModel= ViewModelProvider(this).get(CarViewModel::class.java)
        carViewModel.AddCar(marque,model,desription,image)
        carViewModel._CarLiveData .observe(this, Observer<Car>{
            if (it!=null){
                Toast.makeText(applicationContext,  file.name, Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(applicationContext,  file.name, Toast.LENGTH_LONG).show()
            }
        })
    }





}






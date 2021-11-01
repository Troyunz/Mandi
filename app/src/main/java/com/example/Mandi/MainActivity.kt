package com.example.Mandi

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.example.Mandi.ViewModel.MainViewModel
import com.example.Mandi.ViewModel.MainViewModelFactory
import com.example.Mandi.databinding.ActivityMainBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import java.util.*


class MainActivity : AppCompatActivity() {

    val CAMERA_PERMISSION_CODE = 101
    val CAMERA_REQUEST_CODE = 102
    lateinit var showimage:ImageView
    var fileuri:Uri? = null

    var currentPhotoPath: String? = null
    private lateinit var binding: ActivityMainBinding
    val REQUEST_IMAGE_CAPTURE = 1

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val captureimage = findViewById<Button>(R.id.captureimage)
        var checked:Boolean = false
        val name = findViewById<TextView>(R.id.name)
        val mail = findViewById<TextView>(R.id.mail)
        showimage = findViewById(R.id.showimage)
        val submit = findViewById<Button>(R.id.submit)
        val showname = findViewById<TextView>(R.id.showname)
        val showmail = findViewById<TextView>(R.id.showmail)

        mail.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(mail.text.toString()).matches())
                {
                    checked = true
                }
                else
                {
                    mail.setError("Invalid Email!")
                }
            }

        })

        
        captureimage.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }

        val repository = (applicationContext as FApplication).fRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        binding.gotomandi.setOnClickListener {
            val intent = Intent(this, MandiActivity::class.java)
            startActivity(intent)
        }


        submit.setOnClickListener {
            if(fileuri == null)
            {
                Toast.makeText(this, "Please Capture somthing.", Toast.LENGTH_SHORT).show()
            }
            if(!name.text.isEmpty() && !mail.text.isEmpty()) {
                mainViewModel.insertData(this, 0, name.text.toString(), mail.text.toString(), fileuri.toString())
            }
            else
            {
                Toast.makeText(this, "Enter Somthing!", Toast.LENGTH_SHORT).show()
            }
        }

        mainViewModel.getLoginDetails(this)!!.observe(this, {
            if(it == null)
            {
                Toast.makeText(this, "Enter Something", Toast.LENGTH_SHORT).show()
            }
            else
            {
                showname.text = it.name.toString()
                showmail.text = it.mail.toString()
                showimage.setImageURI(it.image.toUri())
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!
            fileuri = uri

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }

    }
}
package com.krishana.linescan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    private var qrScanIntegrator: IntentIntegrator? = null
    private lateinit var QrResultLauncher : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var imageView = findViewById<ImageView>(R.id.QR_code_imageView)
        imageView.setOnClickListener(){scanQRcode()}
        QrResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode== RESULT_OK){
                val result = findViewById<TextView>(R.id.result)
                result.text = "Result - " + IntentIntegrator.parseActivityResult(it.resultCode,it.data).contents
                Toast.makeText(this,"Here",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Here error",Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun scanQRcode() {
        val qrScanIntegrator = IntentIntegrator(this)
        qrScanIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
//        qrScanIntegrator.initiateScan()
        QrResultLauncher.launch(qrScanIntegrator.createScanIntent())
    }

}
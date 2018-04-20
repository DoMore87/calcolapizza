package com.domore.calcolapizza

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.serialization.json.JSON
import android.content.Intent
import android.net.Uri
import java.io.File
import java.util.*


class ResultActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(view: View) {
        when(view.id){
            R.id.btn_screen -> takeScreenshot()
            R.id.btn_share -> shareScreenshot()
        }
    }

    private fun shareScreenshot() {
        val file = takeScreenshot()
        val uri = Uri.fromFile(file)//Convert file path into Uri for sharing
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "image/*"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "")
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "")
        intent.putExtra(Intent.EXTRA_STREAM, uri)//pass uri here
        startActivity(Intent.createChooser(intent, ""))
    }

    private fun takeScreenshot() : File {
        val generator = Random()
        val n = generator.nextInt(999999)
        val bitmap = ScreenshotUtils.getScreenShot(result_view)
        val saveFile = ScreenshotUtils.getMainDirectoryName()
        return ScreenshotUtils.store(bitmap, "screenshot-$n.jpg", saveFile)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        checkPermission()
        btn_screen.setOnClickListener(this)
        btn_share.setOnClickListener(this)
        val result = PizzaCalculator.Calculate(JSON.parse(intent.getStringExtra("INPUT_DATA")))

        txt_acqua.text = "%.0f g".format(result.acqua)
        txt_farina.text = "%.0f g".format(result.farina)
        txt_lievito.text = "%.2f g".format(result.lievito)
        txt_olio.text = "%.0f g".format(result.grassi)
        txt_sale.text = "%.0f g".format(result.sale)
        txt_prd.text = "%.2f g".format(result.pdr)
    }

    fun checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),11)
        }
    }
}

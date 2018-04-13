package com.domore.calcolapizza

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.serialization.json.JSON
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result = PizzaCalculator.Calculate(JSON.parse(intent.getStringExtra("INPUT_DATA")))

        txt_acqua.text = result.acqua.toString()
        txt_farina.text = result.farina.toString()
        txt_lievito.text = result.lievito.toString()
        txt_olio.text = result.grassi.toString()
        txt_sale.text = result.sale.toString()
        txt_prd.text = result.pdr.toString()


    }
}

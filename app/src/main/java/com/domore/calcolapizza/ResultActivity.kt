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

        txt_acqua.text = "%.0f g".format(result.acqua)
        txt_farina.text = "%.0f g".format(result.farina)
        txt_lievito.text = "%.2f g".format(result.lievito)
        txt_olio.text = "%.0f g".format(result.grassi)
        txt_sale.text = "%.0f g".format(result.sale)
        txt_prd.text = "%.2f g".format(result.pdr)


    }
}

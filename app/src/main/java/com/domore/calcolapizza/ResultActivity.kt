package com.domore.calcolapizza

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.serialization.json.JSON

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        PizzaCalculator.Calculate(JSON.parse(intent.getStringExtra("INPUT_DATA")))

    }
}

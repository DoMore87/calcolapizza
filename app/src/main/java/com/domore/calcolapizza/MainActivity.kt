package com.domore.calcolapizza

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View?) {
        Toast.makeText(this, "Test message", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_calcola.setOnClickListener(this)
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.pdr_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pdr_types.adapter = adapter
    }
}
package com.domore.calcolapizza

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.json.JSON

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener {

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(!(view is EditText) || hasFocus)
            return
        validateEditText(view)
    }

    private fun validateEditText(editText: EditText){
        if(editText.text.toString().isBlank() ) {
            editText.error = "Il campo non può essere vuoto"
            return
        }
        val error = InputValidatorFactory.GetFor(editText.id)(editText.text.toString())
        if(error != "")
            editText.error = error
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, ResultActivity::class.java)

        val editTexts = listOf(panielli, pesoPanielli, idro, sale, oreLievitazione, frigo, temperatura, grassi, pdr);
        editTexts.map { it -> validateEditText(it) }
        val valuesWithErrors = editTexts.filter { it.error != null && !it.error.isBlank() }

        if(valuesWithErrors.isEmpty()) {
            val data = JSON.stringify(InputData(
                    panielli.text.toString().toInt(),
                    pesoPanielli.text.toString().toFloat(),
                    idro.text.toString().toFloat(),
                    sale.text.toString().toFloat(),
                    oreLievitazione.text.toString().toFloat(),
                    frigo.text.toString().toFloat(),
                    temperatura.text.toString().toFloat(),
                    grassi.text.toString().toFloat(),
                    pdr.text.toString().toFloat(), 0))
            intent.putExtra("INPUT_DATA", data)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_calcola.setOnClickListener(this)

        panielli.onFocusChangeListener = this
        pesoPanielli.onFocusChangeListener = this
        idro.onFocusChangeListener = this
        sale.onFocusChangeListener = this
        oreLievitazione.onFocusChangeListener = this
        frigo.onFocusChangeListener = this
        temperatura.onFocusChangeListener = this
        grassi.onFocusChangeListener = this
        pdr.onFocusChangeListener = this


        val adapter = ArrayAdapter.createFromResource(this, R.array.pdr_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pdr_types.adapter = adapter
    }


}
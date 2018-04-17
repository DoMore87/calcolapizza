package com.domore.calcolapizza

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.json.JSON

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener {

    private var textBoxes : List<EditText> = emptyList()

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(!(view is EditText) || hasFocus)
            return
        validateValues()
    }

    private fun validateEditText(editText: EditText){
        val textValues = textBoxes.associateBy ( {it.id}, {it.text.toString()} )
        val nw = textValues
                .plus(Pair(switchTeglia.id, switchTeglia.isChecked.toString()))
                .plus(Pair(pdr_types.id, pdr_types.selectedItemPosition.toString()))

        if(editText.text.toString().isBlank() ) {
            editText.error = "Il campo non può essere vuoto"
            return
        }
        val error = InputValidatorFactory.GetFor(editText.id, nw)(editText.text.toString())
        if(error != "")
            editText.error = error
    }

    private fun validateValues(){
        textBoxes.forEach { validateEditText(it) }
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, ResultActivity::class.java)
        validateValues()
        val valuesWithErrors = textBoxes.filter { it.error != null && !it.error.isBlank() }

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
                    pdr.text.toString().toFloat(),
                    pdr_types.selectedItemPosition,
                    switchTeglia.isChecked))
            intent.putExtra("INPUT_DATA", data)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_calcola.setOnClickListener(this)

        panielli.onFocusChangeListener = this
        panielli.setText("1")
        pesoPanielli.onFocusChangeListener = this
        pesoPanielli.setText("200")
        idro.onFocusChangeListener = this
        idro.setText("65")
        sale.onFocusChangeListener = this
        sale.setText("50")
        oreLievitazione.onFocusChangeListener = this
        oreLievitazione.setText("24")
        frigo.onFocusChangeListener = this
        frigo.setText("0")
        temperatura.onFocusChangeListener = this
        temperatura.setText("20")
        grassi.onFocusChangeListener = this
        grassi.setText("0")
        pdr.onFocusChangeListener = this
        pdr.setText("0")
        switchTeglia.setOnCheckedChangeListener { _, _ -> validateValues() }
        pdr_types.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                validateValues()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        val adapter = ArrayAdapter.createFromResource(this, R.array.pdr_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pdr_types.adapter = adapter

        textBoxes = listOf(panielli, pesoPanielli, idro, sale, oreLievitazione, frigo, temperatura, grassi, pdr)
    }


}
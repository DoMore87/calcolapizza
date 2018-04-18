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
    private val isDebug = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isDebug)
            initializeEditTexts()
        textBoxes = listOf(panielli, pesoPanielli, idro, sale, oreLievitazione, frigo, temperatura, grassi, pdr)
        textBoxes.forEach { it.onFocusChangeListener = this }
        btn_calcola.setOnClickListener(this)
        switchTeglia.setOnCheckedChangeListener { _, _ -> validateAllInputs() }
        pdr_types.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                validateAllInputs()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        val adapter = ArrayAdapter.createFromResource(this, R.array.pdr_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pdr_types.adapter = adapter
    }

    private fun initializeEditTexts(){
        panielli.setText("1")
        pesoPanielli.setText("200")
        idro.setText("65")
        sale.setText("50")
        oreLievitazione.setText("24")
        frigo.setText("0")
        temperatura.setText("20")
        grassi.setText("0")
        pdr.setText("0")
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, ResultActivity::class.java)
        validateAllInputs()

        if(textBoxes.all { it.error == null || it.error.isBlank() }) {
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

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if(!(view is EditText) || hasFocus)
            return
        validateAllInputs()
    }

    private fun validateAllInputs(){
        textBoxes.forEach { validateEditText(it) }
    }

    private fun validateEditText(editText: EditText){

        val valuesMap = textBoxes
                .associateBy( {it.id}, {it.text.toString()} )
                .plus(Pair(switchTeglia.id, switchTeglia.isChecked.toString()))
                .plus(Pair(pdr_types.id, pdr_types.selectedItemPosition.toString()))

        if(editText.text.toString().isBlank() ) {
            editText.error = "Il campo non può essere vuoto"
            return
        }
        val validationFunction = InputValidatorFactory.GetFor(editText.id, valuesMap)
        val error = validationFunction(editText.text.toString())
        if(error != "")
            editText.error = error
    }




}
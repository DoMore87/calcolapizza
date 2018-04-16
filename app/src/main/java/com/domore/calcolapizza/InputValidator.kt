package com.domore.calcolapizza


object InputValidatorFactory {
    fun GetFor(id: Int, textValues: Map<Int, String>) : (String) -> (String) = {
        when(id){
            R.id.panielli -> {
                val intValue = it.toIntOrNull();
                if(intValue != null && intValue > 0)
                    ""
                else
                    "Inserisci un valore > 0"
            }
            R.id.pesoPanielli -> {
                val floatValue = it.toFloatOrNull();
                if(floatValue != null && floatValue > 0)
                    ""
                else
                    "Inserisci un valore > 0"
            }
            R.id.idro -> {
                val floatValue = it.toFloatOrNull();
                if(floatValue != null && floatValue >= 50 && floatValue <= 100)
                    ""
                else
                    "Inserisci un valore tra 50 e 100"
            }
            R.id.sale -> {
                val floatValue = it.toFloatOrNull();
                if(floatValue != null && floatValue >= 0 && floatValue <= 70)
                    ""
                else
                    "Inserisci un valore tra 0 e 70"
            }
            R.id.oreLievitazione -> {
                val floatValue = it.toFloatOrNull();
                if(floatValue != null && floatValue >= 3 && floatValue <= 96)
                    ""
                else
                    "Inserisci un valore tra 3 e 96"
            }
            R.id.frigo -> {
                val floatValue = it.toFloatOrNull();
                val maxInFrigo = textValues.getOrElse(R.id.oreLievitazione, {"0"}).toFloat() - 1

                if(floatValue != null && floatValue >= 0 && floatValue <= maxInFrigo)
                    ""
                else
                    "Inserisci un valore tra 0 e $maxInFrigo"
            }
            R.id.temperatura -> {
                val floatValue = it.toFloatOrNull();
                if(floatValue != null && floatValue >= 15 && floatValue <= 35)
                    ""
                else
                    "Inserisci un valore tra 15 e 35"
            }
            R.id.grassi -> {
                val floatValue = it.toFloatOrNull();
                if(floatValue != null && floatValue >= 0 && floatValue <= 150)
                    ""
                else
                    "Inserisci un valore tra 0 e 150"
            }
            R.id.pdr -> {
                val floatValue = it.toFloatOrNull();
                val maxValue = PizzaCalculator.CalculatePrdLimit(
                        textValues.getOrElse(R.id.sale, {"0"}).toFloat(),
                        textValues.getOrElse(R.id.grassi, {"0"}).toFloat(),
                        textValues.getOrElse(R.id.idro, {"0"}).toFloat(),
                        textValues.getOrElse(R.id.oreLievitazione, {"0"}).toFloat(),
                        textValues.getOrElse(R.id.frigo, {"0"}).toFloat(),
                        textValues.getOrElse(R.id.temperatura, {"0"}).toFloat(),
                        textValues.getOrElse(R.id.pdr_types, {"0"}).toInt(),
                        textValues.getOrElse(R.id.switchTeglia, {"false"}).toBoolean())
                if(floatValue != null && floatValue >= 0 && floatValue <= maxValue)
                    ""
                else
                    "Inserisci un valore tra 0 e $maxValue"
            }
            else -> { ""  }
        }
    }
}
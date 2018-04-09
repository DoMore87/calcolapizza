package com.domore.calcolapizza


object InputValidatorFactory {
    fun GetFor(id : Int) : (String) -> (Boolean) = {
        when(id){
            R.id.panielli -> { val intValue = it.toIntOrNull(); intValue != null && intValue > 0 }
            R.id.pesoPanielli -> { val floatValue = it.toFloatOrNull(); floatValue != null && floatValue > 0 }
            R.id.idro -> { val floatValue = it.toFloatOrNull(); floatValue != null && floatValue >= 50 && floatValue <= 100 }
            R.id.sale -> { val floatValue = it.toFloatOrNull(); floatValue != null && floatValue >= 0 && floatValue <= 70 }
            R.id.oreLievitazione -> { val floatValue = it.toFloatOrNull(); floatValue != null && floatValue >= 3 && floatValue <= 96 }
            R.id.frigo -> { val floatValue = it.toFloatOrNull(); floatValue != null && floatValue >= 0 }
            R.id.temperatura -> { val floatValue = it.toFloatOrNull(); floatValue != null && floatValue >= 15 && floatValue <= 35 }
            R.id.grassi -> { val floatValue = it.toFloatOrNull(); floatValue != null && floatValue >= 0 && floatValue <= 150 }
            R.id.pdr -> { val floatValue = it.toFloatOrNull(); floatValue != null && floatValue > 0 }
            else -> { true  }
        }
    }
}
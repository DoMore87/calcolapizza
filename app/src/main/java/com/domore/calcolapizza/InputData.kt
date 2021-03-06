package com.domore.calcolapizza
import kotlinx.serialization.Serializable

@Serializable
data class InputData(val numPanielli : Int,
                     val pesoPanielli: Float,
                     val idro: Float,
                     val salePerLitro: Float,
                     val oreLievitazione: Float,
                     val inFrigo: Float,
                     val tempAmbiente: Float,
                     val grassi: Float,
                     val prd: Float,
                     val tipoPdr: Int,
                     val inTeglia: Boolean)
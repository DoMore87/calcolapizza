package com.domore.calcolapizza

import kotlin.math.ceil

object PizzaCalculator {

    fun Calculate(input: InputData) : ResultData {
        val inTeglia = 0;
        val gradiNormalizzati = input.tempAmbiente * (1 - 0.25 * inTeglia)

        val coeffPdr = when(input.tipoPdr){
            1 -> 0.00333
            2 -> 0.005
            3 -> 0.01
            else -> 0.0
        }

        val oreNormalizzate = (input.oreLievitazione - 9 * input.inFrigo / 10).toDouble()

        val pesoTot = input.pesoPanielli * input.numPanielli
        val coeff = input.idro * (input.salePerLitro + input.grassi) + 1000 * (input.idro + 100)

        val h = 2250 * (1 + input.salePerLitro / 200) * (1 + input.grassi / 300) / ((4.2 * input.idro - 80 - .0305 * input.idro * input.idro) * Math.pow(gradiNormalizzati, 2.5) * Math.pow(oreNormalizzate, 1.2))

        val pdrRes = ceil(pesoTot * input.prd / 100)
        val farinaRes = ceil(100000 * (pesoTot - pdrRes) / coeff)
        val acquaRes = ceil((1000 * input.idro * (pesoTot - pdrRes) / coeff))
        val saleRes = ceil(input.salePerLitro * input.idro * (pesoTot - pdrRes) / coeff)
        val grassiRes = ceil(input.grassi * input.idro * (pesoTot - pdrRes) / coeff)
        val lievitoRes = ceil((farinaRes * h - coeffPdr * pdrRes).toFloat())

        return ResultData(acquaRes,farinaRes,saleRes,grassiRes,pdrRes,lievitoRes)
    }


    /*var a = 0;


        var oreNormalizzate = oreLievitazione - 9 * inFrigo / 10
          , coeffForza = 81.4206918743428 + 78.3939060802556 * Math.log(oreLievitazione)  // not used
          , forzaConsigliata = 10 * Math.round(coeffForza / 10) // not used
          , pesoTot = panielli * pesoPanielli
          , coeff = idro * (salePerLitro + grassiPerLitro) + 1e3 * (idro + 100)

          , h = 2250 * (1 + salePerLitro / 200) * (1 + grassiPerLitro / 300) / ((4.2 * idro - 80 - .0305 * idro * idro) * Math.pow(gradiNormalizzati, 2.5) * Math.pow(oreNormalizzate, 1.2))
          , F = (1e5 * h * 100 / (1e5 * h + coeff * coeffPdr)).toString().match(/^\d+(?:\.\d{0,2})?/);
        1 == a ? $("#pdrp").parent().attr("title", "Correggere i dati precedenti") : $("#pdrp").parent().attr("title", "Inserire un numero tra 0 e " + F), isNaN(pastaRiporto) || pastaRiporto < 0 || pastaRiporto > F ? ($("#pdrp").parent().addClass("errato"), a = 1) : $("#pdrp").parent().removeClass("errato");

        var pdrRes = pesoTot * pastaRiporto / 100
          , farinaRes = 1e5 * (pesoTot - pdrRes) / coeff
          , acquaRes = (1e3 * idro * (pesoTot - pdrRes) / coeff).toFixed(0)
          , saleRes = (salePerLitro * idro * (pesoTot - pdrRes) / coeff).toFixed(0)
          , grassiRes = (grassiPerLitro * idro * (pesoTot - pdrRes) / coeff).toFixed(0)
          , lievitoRes = (farinaRes * h - coeffPdr * pdrRes).toFixed(2);
        farinaRes = farinaRes.toFixed(0),
        pdrRes = pdrRes.toFixed(0),*/

}
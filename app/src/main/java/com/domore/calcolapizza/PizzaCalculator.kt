package com.domore.calcolapizza

object PizzaCalculator {

    fun Calculate(input: InputData) : ResultData {

        val coeff = CalculateCoeff(input.salePerLitro, input.grassi, input.idro)
        val coeffPdr = CalculatePdrCoeff(input.tipoPdr)
        val h = CalculateH(input.salePerLitro,
                input.grassi,
                input.idro,
                input.oreLievitazione,
                input.inFrigo,
                input.tempAmbiente,
                input.inTeglia)

        val pesoTot = input.pesoPanielli * input.numPanielli
        val pdrRes = (pesoTot * input.prd / 100)
        val farinaRes = (100000 * (pesoTot - pdrRes) / coeff)
        val acquaRes = ((1000 * input.idro * (pesoTot - pdrRes) / coeff))
        val saleRes = (input.salePerLitro * input.idro * (pesoTot - pdrRes) / coeff)
        val grassiRes = (input.grassi * input.idro * (pesoTot - pdrRes) / coeff)
        val lievitoRes = ((farinaRes * h - coeffPdr * pdrRes).toFloat())

        return ResultData(acquaRes,farinaRes,saleRes,grassiRes,pdrRes,lievitoRes)
    }

    fun CalculatePrdLimit(salePerLitro: Float,
                          grassi: Float,
                          idro: Float,
                          oreLievitazione: Float,
                          oreInFrigo: Float,
                          tempAmbiente: Float,
                          tipoPdr: Int,
                          teglia: Boolean) : Double {
        val h = CalculateH(salePerLitro,grassi,idro, oreLievitazione, oreInFrigo, tempAmbiente, teglia)
        val coeffPdr = CalculatePdrCoeff(tipoPdr)
        val coeff = CalculateCoeff(salePerLitro, grassi, idro)
        val pdrLimit = (1e5 * h * 100 / (1e5 * h + coeff * coeffPdr))
        return pdrLimit
    }

    private fun CalculatePdrCoeff(tipoPdr: Int): Double{
        return when(tipoPdr){
            0 -> 0.00333
            1 -> 0.005
            2 -> 0.01
            else -> 0.0
        }
    }

    private fun CalculateCoeff(salePerLitro: Float,
                       grassi: Float,
                       idro: Float) : Float {
        return idro * (salePerLitro + grassi) + 1000 * (idro + 100)
    }

    private fun CalculateH (salePerLitro: Float,
                   grassi: Float,
                   idro: Float,
                   oreLievitazione: Float,
                   oreInFrigo: Float,
                   tempAmbiente: Float,
                   teglia: Boolean) : Double {

        val inTeglia = if (teglia) 1 else 0;
        val gradiNormalizzati = tempAmbiente * (1 - 0.25 * inTeglia)
        val oreNormalizzate = (oreLievitazione - 9 * oreInFrigo / 10).toDouble()
        val h = 2250 * (1 + salePerLitro / 200) * (1 + grassi / 300) / ((4.2 * idro - 80 - .0305 * idro * idro) * Math.pow(gradiNormalizzati, 2.5) * Math.pow(oreNormalizzate, 1.2))
        return h
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
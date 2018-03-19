$(window).load(function() {
    function a() {
        var a = 0;
        var panielli = parseFloat($("#panielli").val().replace(",", "."));   // integer, > 0        
        var pesoPanielli = parseFloat($("#peso").val().replace(",", "."));    // > 0        
        var idro = parseFloat($("#idro").val().replace(",", "."));    // 50 < x < 100
        var salePerLitro = parseFloat($("#salepl").val().replace(",", "."));   //  0 < x < 70        
        var oreLievitazione = parseFloat($("#liev").val().replace(",", "."));  //  3 < x < 96                
        var inFrigo = parseFloat($("#frigo").val().replace(",", "."));   //  0 < x < oreLievitazione - 1
        var inTeglia = $("#teglia").is(":checked"); // bool
        var gradi = parseFloat($("#gradi").val().replace(",", "."));   //  15 < x < 35
        var gradiNormalizzati = gradi * (1 - .25 * inTeglia)
        var grassiPerLitro = parseFloat($("#grassipl").val().replace(",", ".")); //  0 < x < 150

        var pastaRiporto = parseFloat($("#pdrp").val().replace(",", "."));
        switch (parseFloat($('input[name="pdrt"]:checked').val())) {
        case 1:
            coeffPdr = 1 / 300;   // 0,003333333
            break;
        case 2:
            coeffPdr = .005;
            break;
        case 3:
            var coeffPdr = .01
        }
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
        pdrRes = pdrRes.toFixed(0),
        isNaN(forzaConsigliata) || 1 == a ? $("#forza").html("-") : $("#forza").html(forzaConsigliata.toString()),  // not used
        isNaN(farinaRes) || 1 == a ? $("#farina").html("-") : $("#farina").html(farinaRes.toString() + " g"),
        isNaN(acquaRes) || 1 == a ? $("#aqua").html("-") : $("#aqua").html(acquaRes.toString() + " g"),
        isNaN(saleRes) || 1 == a ? $("#sale").html("-") : $("#sale").html(saleRes.toString() + " g"),
        isNaN(grassiRes) || 1 == a ? $("#grassi").html("-") : $("#grassi").html(grassiRes.toString() + " g"),
        isNaN(pdrRes) || 1 == a ? $("#pdr").html("-") : $("#pdr").html(pdrRes.toString() + " g"),
        isNaN(lievitoRes) || 1 == a ? $("#lievito").html("-") : $("#lievito").html(lievitoRes.toString().replace(".", ",") + " g")
    }
    $("body").vegas({
        slides: [{
            src: "piza.jpg"
        }]
    }),
    a(),
    $("input[type='radio']").click(function() {
        a()
    }),
    $("input[type='checkbox']").click(function() {
        a()
    }),
    $("input").keyup(function() {
        a()
    })
});

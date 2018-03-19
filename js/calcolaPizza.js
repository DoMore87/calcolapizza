$(window).load(function() {
    function a() {
        var a = 0
          , r = parseFloat($("#panielli").val().replace(",", "."));
        isNaN(r) || r < 1 || r % 1 != 0 ? ($("#panielli").parent().addClass("errato"),
        a = 1) : $("#panielli").parent().removeClass("errato");
        var e = parseFloat($("#peso").val().replace(",", "."));
        isNaN(e) || e <= 0 ? ($("#peso").parent().addClass("errato"),
        a = 1) : $("#peso").parent().removeClass("errato");
        var t = parseFloat($("#idro").val().replace(",", "."));
        isNaN(t) || t < 50 || t > 100 ? ($("#idro").parent().addClass("errato"),
        a = 1) : $("#idro").parent().removeClass("errato");
        var l = parseFloat($("#salepl").val().replace(",", "."));
        isNaN(l) || l < 0 || l > 70 ? ($("#salepl").parent().addClass("errato"),
        a = 1) : $("#salepl").parent().removeClass("errato");
        var i = parseFloat($("#liev").val().replace(",", "."));
        isNaN(i) || i < 3 || i > 96 ? ($("#liev").parent().addClass("errato"),
        a = 1) : $("#liev").parent().removeClass("errato");
        var s = parseFloat($("#frigo").val().replace(",", "."));
        $("#frigo").parent().attr("title", "Inserire un numero non negativo"),
        (isNaN(s) || s < 0 || s > i - 1) && 0 != s ? (s > i - 1 && $("#frigo").parent().attr("title", "Non superare il totale ore diminuito di 1"),
        $("#frigo").parent().addClass("errato"),
        a = 1) : $("#frigo").parent().removeClass("errato");
        var o = $("#teglia").is(":checked");
        console.log("teglia = " + o);
        var p = parseFloat($("#gradi").val().replace(",", "."));
        isNaN(p) || p < 15 || p > 35 ? ($("#gradi").parent().addClass("errato"),
        a = 1) : $("#gradi").parent().removeClass("errato");
        var n = p * (1 - .25 * o)
          , d = parseFloat($("#grassipl").val().replace(",", "."));
        isNaN(d) || d < 0 || d > 150 ? ($("#grassipl").parent().addClass("errato"),
        a = 1) : $("#grassipl").parent().removeClass("errato");
        var v = parseFloat($("#pdrp").val().replace(",", "."));
        switch (parseFloat($('input[name="pdrt"]:checked').val())) {
        case 1:
            g = 1 / 300;
            break;
        case 2:
            g = .005;
            break;
        case 3:
            var g = .01
        }
        var c = i - 9 * s / 10
          , N = 81.4206918743428 + 78.3939060802556 * Math.log(i)
          , m = 10 * Math.round(N / 10)
          , h = 2250 * (1 + l / 200) * (1 + d / 300) / ((4.2 * t - 80 - .0305 * t * t) * Math.pow(n, 2.5) * Math.pow(c, 1.2))
          , u = r * e
          , C = t * (l + d) + 1e3 * (t + 100)
          , F = (1e5 * h * 100 / (1e5 * h + C * g)).toString().match(/^\d+(?:\.\d{0,2})?/);
        1 == a ? $("#pdrp").parent().attr("title", "Correggere i dati precedenti") : $("#pdrp").parent().attr("title", "Inserire un numero tra 0 e " + F),
        isNaN(v) || v < 0 || v > F ? ($("#pdrp").parent().addClass("errato"),
        a = 1) : $("#pdrp").parent().removeClass("errato");
        var f = u * v / 100
          , k = 1e5 * (u - f) / C
          , S = (1e3 * t * (u - f) / C).toFixed(0)
          , x = (l * t * (u - f) / C).toFixed(0)
          , w = (d * t * (u - f) / C).toFixed(0)
          , b = (k * h - g * f).toFixed(2);
        k = k.toFixed(0),
        f = f.toFixed(0),
        isNaN(m) || 1 == a ? $("#forza").html("-") : $("#forza").html(m.toString()),
        isNaN(k) || 1 == a ? $("#farina").html("-") : $("#farina").html(k.toString() + " g"),
        isNaN(S) || 1 == a ? $("#aqua").html("-") : $("#aqua").html(S.toString() + " g"),
        isNaN(x) || 1 == a ? $("#sale").html("-") : $("#sale").html(x.toString() + " g"),
        isNaN(w) || 1 == a ? $("#grassi").html("-") : $("#grassi").html(w.toString() + " g"),
        isNaN(f) || 1 == a ? $("#pdr").html("-") : $("#pdr").html(f.toString() + " g"),
        isNaN(b) || 1 == a ? $("#lievito").html("-") : $("#lievito").html(b.toString().replace(".", ",") + " g")
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

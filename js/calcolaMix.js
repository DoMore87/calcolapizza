$(window).load(function() {
    $("body").vegas({
        slides: [{
            src: "piza.jpg"
        }]
    });
    $(".triofarine").hide();
    $("#nfar2").prop("checked", !0);
    duofarine();
    farinismo();
    $("input#nfar2").click(function() {
        $(".triofarine").hide();
        duofarine();
        farinismo()
    });
    $("input#nfar3").click(function() {
        $(".triofarine").show();
        triofarine();
        farinismo()
    });
    $("input.farin1").keyup(function() {
        $("#nfar3").is(":checked") ? triofarine() : duofarine();
        setTimeout(farinismo, 500)
    });
    $("input#mix").keyup(function() {
        delay(function() {
            var d = parseFloat($("#gram1").val().replace(",", "."))
              , b = parseFloat($("#gram2").val().replace(",", "."))
              , c = parseFloat($("#w1").val().replace(",", "."))
              , a = parseFloat($("#w2").val().replace(",", "."))
              , e = parseFloat($("#mix").val().replace(",", "."));
            if ($("#nfar3").is(":checked")) {
                var f = parseFloat($("#gram3").val().replace(",", "."))
                  , n = parseFloat($("#w3").val().replace(",", "."));
                b = d + b + f;
                d = Math.max(c, a, n);
                f = Math.min(c, a, n);
                c = [c, a, n];
                for (a = 0; 3 > a; a++)
                    if (c[a] == d) {
                        var g = a;
                        break
                    }
                for (a = 0; 3 > a; a++)
                    if (c[a] == f && a != g) {
                        var h = a;
                        break
                    }
                for (a = 0; 3 > a; a++)
                    if (a != h && a != g) {
                        var m = a;
                        break
                    }
                c = c[m];
                g += 1;
                h += 1;
                m += 1;
                parseFloat($("#gram" + m).val().replace(",", "."));
                parseFloat($("#gram" + g).val().replace(",", "."));
                parseFloat($("#gram" + h).val().replace(",", "."));
                for (a = 0; 10 >= a; a++) {
                    var k = 1 * (1 - a / 10) / 3;
                    var p = (e - f - (c - f) * k) / (d - f);
                    var l = 1 - k - p;
                    if (0 < k && 0 < p && 0 < l)
                        break
                }
                e = parseInt((p * b).toFixed(0), 10);
                l = parseInt((l * b).toFixed(0), 10);
                k = parseInt((k * b).toFixed(0), 10);
                b = e + k + l - b;
                0 != b && (e > l ? e -= b : l -= b);
                $("#gram" + g).val(e);
                $("#gram" + h).val(l);
                $("#gram" + m).val(k)
            } else
                b = d + b,
                h = (e - a) / (c - a),
                g = 1 - h,
                d = (h * b).toFixed(0),
                b = (g * b).toFixed(0),
                $("#gram1").val(d),
                $("#gram2").val(b);
            setTimeout(farinismo, 500)
        }, 1E3)
    })
});
function duofarine() {
    var d = parseFloat($("#w1").val().replace(",", "."))
      , b = parseFloat($("#w2").val().replace(",", "."))
      , c = parseFloat($("#gram1").val().replace(",", "."))
      , a = parseFloat($("#gram2").val().replace(",", "."));
    d = (c / (c + a) * d + a / (c + a) * b).toFixed(0);
    $("#mix").val(d)
}
function triofarine() {
    var d = parseFloat($("#w1").val().replace(",", "."))
      , b = parseFloat($("#w2").val().replace(",", "."))
      , c = parseFloat($("#gram1").val().replace(",", "."))
      , a = parseFloat($("#gram2").val().replace(",", "."))
      , e = parseFloat($("#gram3").val().replace(",", "."))
      , f = parseFloat($("#w3").val().replace(",", "."));
    d = (c / (c + a + e) * d + a / (c + a + e) * b + e / (c + a + e) * f).toFixed(0);
    $("#mix").val(d)
}
function farinismo() {
    var d = parseFloat($("#w1").val().replace(",", "."));
    isNaN(d) || 90 > d || 450 < d ? $("#w1").parent().addClass("errato") : $("#w1").parent().removeClass("errato");
    var b = parseFloat($("#gram1").val().replace(",", "."));
    isNaN(b) || 0 > b || 2E5 < b ? $("#gram1").parent().addClass("errato") : $("#gram1").parent().removeClass("errato");
    var c = parseFloat($("#w2").val().replace(",", "."));
    isNaN(c) || 90 > c || 450 < c ? $("#w2").parent().addClass("errato") : $("#w2").parent().removeClass("errato");
    b = parseFloat($("#gram2").val().replace(",", "."));
    isNaN(b) || 0 > b || 2E5 < b ? $("#gram2").parent().addClass("errato") : $("#gram2").parent().removeClass("errato");
    var a = parseFloat($("#w3").val().replace(",", "."));
    isNaN(a) || 90 > a || 450 < a ? $("#w3").parent().addClass("errato") : $("#w3").parent().removeClass("errato");
    b = parseFloat($("#gram3").val().replace(",", "."));
    isNaN(b) || 0 > b || 2E5 < b ? $("#gram3").parent().addClass("errato") : $("#gram3").parent().removeClass("errato");
    $("#nfar3").is(":checked") ? (b = Math.min(d, c, a),
    d = Math.max(d, c, a)) : (b = Math.min(d, c),
    d = Math.max(d, c));
    $("#mix").parent().attr("title", "Inserire un numero tra " + b + " e " + d);
    c = parseFloat($("#mix").val().replace(",", "."));
    isNaN(c) || c < b || c > d ? $("#mix").parent().addClass("errato") : $("#mix").parent().removeClass("errato")
}
var delay = function() {
    var d = 0;
    return function(b, c) {
        clearTimeout(d);
        d = setTimeout(b, c)
    }
}();

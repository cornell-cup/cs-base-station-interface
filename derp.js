function getBID() {
    return $('#bid').val();
}

function getIP() {
    return $('#ip').val();
}

function getPower() {
    return $('#pow').val();
}

function sendMotors(fl, fr, bl, br) {
    $.ajax({
        method: "POST",
        url: getIP() + "/commandBot",
        data: JSON.stringify({
            bid: getBID(),
            fl: fl,
            fr: fr,
            bl: bl,
            br: br
        }),
        processData: false,
        contentType: 'application/json'
    });
}

$('#f').click(function () {
    var p = getPower();
    sendMotors(p, p, p, p);
});

$('#b').click(function () {
    var p = getPower();
    sendMotors(-p, -p, -p, -p);
});

$('#r').click(function () {
    var p = getPower();
    sendMotors(p, -p, -p, p);
});

$('#l').click(function () {
    var p = getPower();
    sendMotors(-p, p, p, -p);
});

$('#cc').click(function () {
    var p = getPower();
    sendMotors(-p, p, -p, p);
});

$('#c').click(function () {
    var p = getPower();
    sendMotors(p, -p, p, -p);
});

$('#s').click(function () {
    var p = getPower();
    sendMotors(0, 0, 0, 0);
});

$('#addbot').click(function () {
    botjax();
});

function botjax() {
    $.ajax({
        method: "POST",
        url: getIP() + "/addBot",
        data: JSON.stringify({
            ip: $('#bip').val(),
            port: $('#botport').val()
        }),
        processData: false,
        contentType: 'application/json'
    });
}
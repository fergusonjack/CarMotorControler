$(".up").click(function (data,status) {
    $.get( "piper.php", { a: "up"} );
    console.log(data + status);
});

$(".left").click(function () {
    $.get( "piper.php", { a: "left"} );
});

$(".right").click(function () {
    $.get( "piper.php", { a: "right"} );
});

$(".down").click(function () {
    $.get( "piper.php", { a: "down"} );
});
//need to add sockets in to communicate with java and therefore pi4j

<?php
/* Allow the script to hang around waiting for connections. */
set_time_limit(0);

/* Turn on implicit output flushing so we see what we're getting
 * as it comes in. */
ob_implicit_flush();

$address = '127.0.0.1';
$port = 4444;

if (($sock = socket_create(AF_INET, SOCK_STREAM, SOL_TCP)) === false) {
    echo "socket_create() failed: reason: " . socket_strerror(socket_last_error()) . "\n";
}

if (socket_bind($sock, $address, $port) === false) {
    echo "socket_bind() failed: reason: " . socket_strerror(socket_last_error($sock)) . "\n";
}

if (socket_listen($sock, 5) === false) {
    echo "socket_listen() failed: reason: " . socket_strerror(socket_last_error($sock)) . "\n";
}


$myfile = fopen("testfile.txt", "w");
if (isset($_GET['a'])){
    switch (htmlspecialchars($_GET["a"])){

        case "up":
            fwrite($myfile, "up");
            break;
        case "left":
            fwrite($myfile, "left");
            break;
        case "right":
            fwrite($myfile, "right");
            break;
        case "down":
            fwrite($myfile, "down");
            break;
        default:
            fwrite($myfile, "def");
            break;
    }
    fclose($myfile);
} else {
    fwrite($myfile, "1234");
    fclose($myfile);
}
?>
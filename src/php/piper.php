//need to add sockets in to communicate with java and therefore pi4j

<?php
/* Allow the script to hang around waiting for connections. */
set_time_limit(0);

/* Turn on implicit output flushing so we see what we're getting
 * as it comes in. */
ob_implicit_flush();

$address = '127.0.0.1';
$port = 4444;
$inside = true;
$outside = true;

if (($sock = socket_create(AF_INET, SOCK_STREAM, SOL_TCP)) === false) {
    echo "socket_create() failed: reason: " . socket_strerror(socket_last_error()) . "\n";
}

if (socket_bind($sock, $address, $port) === false) {
    echo "socket_bind() failed: reason: " . socket_strerror(socket_last_error($sock)) . "\n";
}

if (socket_listen($sock, 5) === false) {
    echo "socket_listen() failed: reason: " . socket_strerror(socket_last_error($sock)) . "\n";
}

do {
    if (($msgsock = socket_accept($sock)) === false) {
        echo "socket_accept() failed: reason: " . socket_strerror(socket_last_error($sock)) . "\n";
        break;
    }

    do {
        if (isset($_GET['a'])){
            switch (htmlspecialchars($_GET["a"])){

                case "up":
                    socket_write($msgsock, "up",2);
                    break;
                case "left":
                    socket_write($msgsock, "left", 4);
                    break;
                case "right":
                    socket_write($msgsock, "right", 5);
                    break;
                case "down":
                    socket_write($msgsock, "down", 4);
                    break;
                default:
                    fwrite($myfile, "def");
                    break;
            }

        } else {
            continue;
        }

    } while ($inside);
    socket_close($msgsock);
} while ($outside);

socket_close($sock);
?>
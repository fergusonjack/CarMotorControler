
<?php
$address = 'localhost';
$port = 6749;
$data = "up";

if (($sock = socket_create(AF_INET, SOCK_STREAM, SOL_TCP)) === false) {
    echo "socket_create() failed: reason: " . socket_strerror(socket_last_error()) . "\n";
} else {
    if (($result = socket_connect($sock, $address, $port)) === FALSE ) {
        echo "socket_connect() failed. Reason: ($result) " . socket_strerror(socket_last_error($socket));
    } else {
		if (isset($_GET['a'])){
			switch (htmlspecialchars($_GET["a"])){
		    	case "up":
		        	socket_write($sock, "up",2);
		            break;
		        case "left":
		            socket_write($sock, "left", 4);
		            break;
		        case "right":
		            socket_write($sock, "right", 5);
		            break;
		        case "down":
		            socket_write($sock, "down", 4);
		            break;
		        default:
					socket_write($sock, "fail" , 2);
			}
		} else {
			socket_write($sock,"fail",2);
		}
	}
}
socket_close($sock);
?>

//need to add sockets in to communicate with java and therefore pi4j

<?php
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
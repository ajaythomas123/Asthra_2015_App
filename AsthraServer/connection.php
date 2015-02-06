<?php
    $mysql_hostname = "mysql.hostinger.in";
    $mysql_user = "u530820259_sjcet";
    $mysql_password = "sjcet2k15";
    $mysql_database = "u530820259_sjcet";
    $prefix = "";
    $bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Could not connect database");
    mysql_select_db($mysql_database, $bd) or die("Could not select database");
    ?>
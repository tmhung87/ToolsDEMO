<?php

require('dbcon.php');

$ten = $_POST['TEN'];
$mt = $_POST['MIEUTA'];
$prn = $_POST['PRN'];
$sn = $_POST['SN'];
$anh = $_POST['ANH'];

$t = time();

$path = "uploads/$ten.$t.png";


$url = "https://1luutru.000webhostapp.com/$path";


$qr = "INSERT INTO tools VALUES(null,'$ten','$mt','$prn','$sn','$url')";

if (mysqli_query($conn,$qr)){
	file_put_contents($path, base64_decode($anh));
	
	echo "thanh cong";
} else {
	echo "loi";
}
?>
<?php
require('dbcon.php');

$id = $_POST['ID'];
$mt = $_POST['MIEUTA'];
$ten = $_POST['TEN'];
$prn = $_POST['PRN'];
$sn = $_POST['SN'];
$anh = $_POST['ANH'];
$t = time();

$path = "uploads/$ten.$t.png";

$url = "https://1luutru.000webhostapp.com/$path";

$qr = "UPDATE tools SET ten = '$ten', mt = '$mt',pn = '$prn', sn = '$sn', anh = '$url' WHERE id = $id";

$qr1 = "UPDATE tools SET ten = '$ten', mt = '$mt',pn = '$prn', sn = '$sn' WHERE id = $id";

$qranh = "SELECT * FROM tools WHERE id = $id";

$result = mysqli_query($conn,$qranh);

while ($row = mysqli_fetch_assoc($result)) {
	$pathcu = $row['anh'];
}



$tennn = strstr($pathcu, 'uploads');

rename( $tennn,$path);

if($anh =="a"){
   mysqli_query($conn,$qr);
    
}else{
    if (mysqli_query($conn,$qr)){
	file_put_contents($path, base64_decode($anh));
	echo "thanh cong";
} else {
	echo "loi";
}
    
};

?>
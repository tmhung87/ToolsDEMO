<?php
require('dbcon.php');

$id = $_POST['ID'];
$ten = $_POST['TEN'];
$anh = $_POST['ANH'];

echo "$anh";

$s = strstr($anh, 'uploads');

echo "$s";

$s2 = str_replace( '.png', '', $s );

echo $s2;

$qr ="DELETE FROM tools WHERE id = $id";

foreach (glob("$s2.*") as $filename) {
    unlink($filename);
}

mysqli_query($conn,$qr);

?>
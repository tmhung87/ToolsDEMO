<?php
require('dbcon.php');

class Tools 
{
	
	function Tools($id,$ten,$mieuta,$pn,$sn,$anh)
	{
		$this->ID = $id;
		$this->TEN = $ten;
		$this->MIEUTA = $mieuta;
		$this->PN = $pn;
		$this->SN = $sn;
		$this->ANH = $anh;
		
	}
}


$query = "SELECT * FROM tools";
$data = mysqli_query($conn,$query);

$mangtools = array();

while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangtools, new Tools(
		$row['id'],
		$row['ten'],
		$row['mt'],
		$row['pn'],
		$row['sn'],
		$row['anh']
	));
}

echo json_encode($mangtools);

?>
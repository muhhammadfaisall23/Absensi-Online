<?php
	include "koneksi.php";
	
	$id = isset($_POST['id']) ? $_POST['id'] : '';
	
	class emp{}
	
	if (empty($id)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Error Mengambil Data"; 
		die(json_encode($response));
	} else {
		$query 	= mysql_query("SELECT * FROM tblkaryawan WHERE id='".$id."'");
		$row 	= mysql_fetch_array($query);
		
		if (!empty($row)) {
			$response = new emp();
			$response->success = 1;
			$response->id = $row["id"];
			$response->nip = $row["nip"];
			$response->pass = $row["pass"];
			$response->nama_karyawan = $row["nama_karyawan"];
			$response->divisi = $row["divisi"];
			$response->unit = $row["unit"];
			$response->lokasi = $row["lokasi"];
			$response->jabatan = $row["jabatan"];
			$response->hp = $row["hp"];
			$response->email = $row["email"];
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Mengambil Data";
			die(json_encode($response)); 
		}	
	}
?>
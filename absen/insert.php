<?php

	include "koneksi.php";
	
	$nip = isset($_POST['nip']) ? $_POST['nip'] : '';
	$pass = isset($_POST['pass']) ? $_POST['pass'] : '';
	$nama_karyawan = isset($_POST['nama_karyawan']) ? $_POST['nama_karyawan'] : '';
	$divisi = isset($_POST['divisi']) ? $_POST['divisi'] : '';
	$unit = isset($_POST['unit']) ? $_POST['unit'] : '';
	$lokasi = isset($_POST['lokasi']) ? $_POST['lokasi'] : '';
	$jabatan = isset($_POST['jabatan']) ? $_POST['jabatan'] : '';
	$hp = isset($_POST['hp']) ? $_POST['hp'] : '';
	$email = isset($_POST['email']) ? $_POST['email'] : '';

	
	class emp{}
	
	if (empty($nip) || empty($pass) || empty($nama_karyawan)|| empty($divisi)|| empty($unit)|| empty($lokasi)|| 
		empty($jabatan)|| empty($hp)|| empty($email)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("INSERT INTO tblkaryawan (id,nip,pass,nama_karyawan,divisi,unit,lokasi,jabatan,hp,email) VALUES(0,'".$nip."','".$pass."','".$nama_karyawan."','".$divisi."','".$unit."','".$lokasi."','".$jabatan."','".$hp	."','".$email."')");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di simpan";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
?>
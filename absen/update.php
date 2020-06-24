<?php
	include "koneksi.php";
	
	$id = isset($_POST['id']) ? $_POST['id'] : '';
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
	
	if (empty($id) || empty($nip) || empty($pass) || empty($nama_karyawan)|| empty($divisi)|| empty($unit)|| empty($lokasi)|| 
		empty($jabatan)|| empty($hp)|| empty($email)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysql_query("UPDATE tblkaryawan SET nip='".$nip."', pass='".$pass."', nama_karyawan='".$nama_karyawan."', divisi='".$divisi."', unit='".$unit."', lokasi='".$lokasi."', jabatan='".$jabatan."', hp='".$hp."', email='".$email."' WHERE id='".$id."'");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di update";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error update Data";
			die(json_encode($response)); 
		}	
	}
?>
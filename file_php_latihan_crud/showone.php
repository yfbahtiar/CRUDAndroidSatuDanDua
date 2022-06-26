<?php
require_once('koneksi.php');

$type = $_GET['type'];
$id = $_GET['id'];
$hasil = array();

if ($type == "login_siswa") {
    $sql = "SELECT * FROM login_siswa WHERE id = '$id'";
    $r = mysqli_query($con, $sql);
    if ($row = mysqli_fetch_array($r)) {
        array_push($hasil, array(
            'id' => $row['id'],
            'username' => $row['username'],
            'password' => $row['password'],
            'status' => $row['status'],
            'email' => $row['email']
        ));
    }
} else if ($type == "siswa") {
    $sql = "SELECT * FROM siswa WHERE id = '$id'";
    $r = mysqli_query($con, $sql);
    while ($row = mysqli_fetch_array($r)) {
        array_push($hasil, array(
            'id' => $row['id'],
            'nama' => $row['nama'],
            'kelas' => $row['kelas'],
            'jurusan' => $row['jurusan'],
            'no_hp' => $row['no_hp'],
            'alamat' => $row['alamat']
        ));
    }
}

echo json_encode(array('result' => $hasil));
mysqli_close($con);

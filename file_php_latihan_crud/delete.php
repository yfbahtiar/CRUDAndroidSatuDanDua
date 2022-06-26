<?php
require_once('koneksi.php');

$type = $_GET['type'];
$id = $_GET['id'];

if ($type == "login_siswa") {
    $sql = "DELETE FROM login_siswa WHERE id = '$id'";
} else if ($type == "siswa") {
    $sql = "DELETE FROM siswa WHERE id = '$id'";
}

if (mysqli_query($con, $sql)) {
    echo "Data berhasil dihapus";
} else {
    echo "Data gagal dihapus";
}

mysqli_close($con);

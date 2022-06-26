<?php
require_once('koneksi.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $type = $_POST['type'];
    $id = $_POST['id'];
    if ($type == "login_siswa") {
        $username = $_POST['username'];
        $password = $_POST['password'];
        $status = $_POST['status'];
        $email = $_POST['email'];

        $sql = "UPDATE login_siswa SET username = '$username', password = '$password', status = '$status', email = '$email' WHERE id = '$id'";
    } else if ($type == "siswa") {
        $nama = $_POST['nama'];
        $kelas = $_POST['kelas'];
        $jurusan = $_POST['jurusan'];
        $no_hp = $_POST['no_hp'];
        $alamat = $_POST['alamat'];

        $sql = "UPDATE siswa SET nama = '$nama', kelas = '$kelas', jurusan = '$jurusan', no_hp = '$no_hp', alamat = '$alamat' WHERE id = '$id'";
    }

    if (mysqli_query($con, $sql)) {
        echo "Data berhasil di ubah";
    } else {
        echo "Data gagal di ubah";
    }

    mysqli_close($con);
}

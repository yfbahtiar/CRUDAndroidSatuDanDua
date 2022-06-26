<?php
require_once('koneksi.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $type = $_POST['type'];
    if ($type == "login_siswa") {
        $username = $_POST['username'];
        $password = $_POST['password'];
        $status = $_POST['status'];
        $email = $_POST['email'];

        $sql = "INSERT INTO login_siswa (username, password, status, email) VALUES ('$username', '$password', '$status', '$email')";
    } else if ($type == "siswa") {
        $nama = $_POST['nama'];
        $kelas = $_POST['kelas'];
        $jurusan = $_POST['jurusan'];
        $no_hp = $_POST['no_hp'];
        $alamat = $_POST['alamat'];

        $sql = "INSERT INTO siswa (nama, kelas, jurusan, no_hp, alamat) VALUES ('$nama', '$kelas', '$jurusan', '$no_hp', '$alamat')";
    }

    if (mysqli_query($con, $sql)) {
        echo "Data berhasil di tambahkan";
    } else {
        echo "Data gagal di tambahkan";
    }

    mysqli_close($con);
}

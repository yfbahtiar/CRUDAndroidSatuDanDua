<?php
define('host', 'localhost');
define('user', 'root');
define('pass', '');
define('db', 'androidcrud');

$con = mysqli_connect(host, user, pass, db) or die('tidak bisa koneksi');

package com.example.androidcrud.config;

public class konfigurasi {

    //url
    public static final String URL_INSERT = "http://192.168.100.23/file_php_latihan_crud/insert.php";
    public static final String URL_SHOWALL = "http://192.168.100.23/file_php_latihan_crud/showall.php?type=";
    public static final String URL_SHOWONE = "http://192.168.100.23/file_php_latihan_crud/showone.php?type=";
    public static final String URL_UPDATE = "http://192.168.100.23/file_php_latihan_crud/update.php";
    public static final String URL_DELETE = "http://192.168.100.23/file_php_latihan_crud/delete.php?type=";

    //json tags
    public static final String TAG_JSON_ARRAY = "result";

    //key for siswa
    public static final String KEY_ID = "id";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_KELAS = "kelas";
    public static final String KEY_JURUSAN = "jurusan";
    public static final String KEY_NOHP = "no_hp";
    public static final String KEY_ALAMAT = "alamat";

    //key for login_siswa
    public static final String KEY_ID_LOGINSISWA = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_STATUS = "status";
    public static final String KEY_EMAIL = "email";

    //key for all
    public static final String KEY_TYPE= "type";
}

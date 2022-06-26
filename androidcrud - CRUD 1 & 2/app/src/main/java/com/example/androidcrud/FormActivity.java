package com.example.androidcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidcrud.config.konfigurasi;
import com.example.androidcrud.modul.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FormActivity extends AppCompatActivity implements View.OnClickListener{

    EditText txtNama, txtKelas, txtJurusan, txtNoHp, txtAlamat;
    Button btnInput, btnHapus;

    private String id, requestTypeOnly, requestTypeWithId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        txtNama = (EditText)findViewById(R.id.txtNama);
        txtKelas = (EditText)findViewById(R.id.txtKelas);
        txtJurusan = (EditText)findViewById(R.id.txtJurusan);
        txtNoHp = (EditText)findViewById(R.id.txtNoHp);
        txtAlamat = (EditText)findViewById(R.id.txtAlamat);
        btnInput = (Button)findViewById(R.id.btnInput);
        btnHapus = (Button)findViewById(R.id.btnHapus);

        // get intent from MainActivity
        Intent intent = getIntent();
        requestTypeOnly = intent.getStringExtra("requestTypeOnly");
        requestTypeWithId = intent.getStringExtra("requestTypeWithId");

        btnInput.setOnClickListener(this);
        btnHapus.setOnClickListener(this);

        btnHapus.setVisibility(View.GONE);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            id = bundle.getString("id");

            if(!id.equals("")){
                btnInput.setText("Ubah");
                btnHapus.setVisibility(View.VISIBLE);
                getSiswa();
            }
        }
    }

    public void tambahSiswa(){
        final String nama = txtNama.getText().toString().trim();
        final String kelas = txtKelas.getText().toString().trim();
        final String jurusan = txtJurusan.getText().toString().trim();
        final String noHp = txtNoHp.getText().toString().trim();
        final String alamat = txtAlamat.getText().toString().trim();

        final String requestTypeOnly = "siswa";
        final String requestTypeWithId = "siswa&id=";

        class TambahSiswa extends AsyncTask<Void, Void, String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FormActivity.this, "Menambahkan Data","Mohon tunggu...",false,false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_NAMA, nama);
                params.put(konfigurasi.KEY_KELAS, kelas);
                params.put(konfigurasi.KEY_JURUSAN, jurusan);
                params.put(konfigurasi.KEY_NOHP, noHp);
                params.put(konfigurasi.KEY_ALAMAT, alamat);
                params.put(konfigurasi.KEY_TYPE, requestTypeOnly);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_INSERT, params);

                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(FormActivity.this, s, Toast.LENGTH_SHORT).show();

                // kembalikan ke MainActivity dengan memabawa intent
                Intent p = new Intent(FormActivity.this, MainActivity.class);
                p.putExtra("requestTypeOnly", requestTypeOnly);
                p.putExtra("requestTypeWithId", requestTypeWithId);
                startActivity(p);
            }
        }

        TambahSiswa ts = new TambahSiswa();
        ts.execute();
    }

    public void getSiswa(){
        class GetSiswa extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FormActivity.this, "Memuat", "Sedang mengambil..", false,false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                String res = rh.sendGetRequestParam(konfigurasi.URL_SHOWONE+requestTypeWithId, id);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                tampilSiswa(s);
            }
        }

        GetSiswa gs = new GetSiswa();
        gs.execute();

    }

    public void tampilSiswa(String json){
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            JSONObject c = result.getJSONObject(0);
            txtNama.setText(c.getString(konfigurasi.KEY_NAMA));
            txtKelas.setText(c.getString(konfigurasi.KEY_KELAS));
            txtJurusan.setText(c.getString(konfigurasi.KEY_JURUSAN));
            txtNoHp.setText(c.getString(konfigurasi.KEY_NOHP));
            txtAlamat.setText(c.getString(konfigurasi.KEY_ALAMAT));

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void ubahSiswa(){
        final String nama = txtNama.getText().toString().trim();
        final String kelas = txtKelas.getText().toString().trim();
        final String jurusan = txtJurusan.getText().toString().trim();
        final String noHp = txtNoHp.getText().toString().trim();
        final String alamat = txtAlamat.getText().toString().trim();
        final String type = requestTypeOnly;

        class UbahSiswa extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FormActivity.this, "Mengubah","Mohon Tunggu..",false,false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_ID, id);
                params.put(konfigurasi.KEY_NAMA, nama);
                params.put(konfigurasi.KEY_KELAS, kelas);
                params.put(konfigurasi.KEY_JURUSAN, jurusan);
                params.put(konfigurasi.KEY_NOHP, noHp);
                params.put(konfigurasi.KEY_ALAMAT, alamat);
                params.put(konfigurasi.KEY_TYPE, type);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_UPDATE, params);

                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(FormActivity.this, s, Toast.LENGTH_SHORT).show();

                // kembalikan ke MainActivity dengan memabawa intent
                Intent intent = new Intent(FormActivity.this, MainActivity.class);
                intent.putExtra("requestTypeOnly", requestTypeOnly);
                intent.putExtra("requestTypeWithId", requestTypeWithId);
                startActivity(intent);
            }
        }

        UbahSiswa us = new UbahSiswa();
        us.execute();
    }

    public void hapusSiswa(){
        class HapusSiswa extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FormActivity.this, "Menghapus", "Mohon tunggu..", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                String res = rh.sendGetRequestParam(konfigurasi.URL_DELETE+requestTypeWithId, id);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(FormActivity.this, s, Toast.LENGTH_SHORT).show();

                // kembalikan ke MainActivity dengan memabawa intent
                Intent intent = new Intent(FormActivity.this, MainActivity.class);
                intent.putExtra("requestTypeOnly", requestTypeOnly);
                intent.putExtra("requestTypeWithId", requestTypeWithId);
                startActivity(intent);
            }
        }

        HapusSiswa hs = new HapusSiswa();
        hs.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == btnInput){
            String txtbutton = btnInput.getText().toString().trim();
            if(txtbutton.equals("Tambah")){
                tambahSiswa();
            }else if(txtbutton.equals("Ubah")){
                ubahSiswa();
            }
        }else if(v == btnHapus){
            hapusSiswa();
        }
    }

}

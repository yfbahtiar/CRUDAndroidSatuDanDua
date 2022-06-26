package com.example.androidcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidcrud.config.konfigurasi;
import com.example.androidcrud.modul.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FormLoginSiswaActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtUsername, txtPassword, txtStatus, txtEmail;
    CheckBox cbShowPass;
    Button btnInput, btnHapus;

    private String id, requestTypeOnly, requestTypeWithId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login_siswa);

        txtUsername = (EditText)findViewById(R.id.txtUsername);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtStatus = (EditText)findViewById(R.id.txtStatus);
        txtEmail = (EditText)findViewById(R.id.txtEmail);

        cbShowPass = (CheckBox)findViewById(R.id.cbShowPass);
        btnInput = (Button)findViewById(R.id.btnInput);
        btnHapus = (Button)findViewById(R.id.btnHapus);

        // get intent from MainActivity
        Intent intent = getIntent();
        requestTypeOnly = intent.getStringExtra("requestTypeOnly");
        requestTypeWithId = intent.getStringExtra("requestTypeWithId");

        cbShowPass.setOnClickListener(this);
        btnInput.setOnClickListener(this);
        btnHapus.setOnClickListener(this);

        btnHapus.setVisibility(View.GONE);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            id = bundle.getString("id");

            if(!id.equals("")){
                btnInput.setText("Ubah");
                btnHapus.setVisibility(View.VISIBLE);
                getLoginSiswa();
            }
        }
    }

    public void tambahLoginSiswa(){
        final String username = txtUsername.getText().toString().trim();
        final String passowrd = txtPassword.getText().toString().trim();
        final String status = txtStatus.getText().toString().trim();
        final String email = txtEmail.getText().toString().trim();

        final String requestTypeOnly = "login_siswa";
        final String requestTypeWithId = "login_siswa&id=";

        class TambahLoginSiswa extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FormLoginSiswaActivity.this, "Menambahkan Data","Mohon tunggu...",false,false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_USERNAME, username);
                params.put(konfigurasi.KEY_PASSWORD, passowrd);
                params.put(konfigurasi.KEY_STATUS, status);
                params.put(konfigurasi.KEY_EMAIL, email);
                params.put(konfigurasi.KEY_TYPE, requestTypeOnly);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_INSERT, params);

                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(FormLoginSiswaActivity.this, s, Toast.LENGTH_SHORT).show();

                // kembalikan ke MainActivity dengan memabawa intent
                Intent p = new Intent(FormLoginSiswaActivity.this, LoginSiswaActivity.class);
                p.putExtra("requestTypeOnly", requestTypeOnly);
                p.putExtra("requestTypeWithId", requestTypeWithId);
                startActivity(p);
            }
        }

        TambahLoginSiswa tls = new TambahLoginSiswa();
        tls.execute();
    }

    public void getLoginSiswa(){
        class GetLoginSiswa extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FormLoginSiswaActivity.this, "Memuat", "Sedang mengambil..", false,false);
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
                tampilLoginSiswa(s);
            }
        }

        GetLoginSiswa gls = new GetLoginSiswa();
        gls.execute();

    }

    public void tampilLoginSiswa(String json){
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            JSONObject c = result.getJSONObject(0);
            txtUsername.setText(c.getString(konfigurasi.KEY_USERNAME));
            txtPassword.setText(c.getString(konfigurasi.KEY_PASSWORD));
            txtStatus.setText(c.getString(konfigurasi.KEY_STATUS));
            txtEmail.setText(c.getString(konfigurasi.KEY_EMAIL));

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void ubahLoginSiswa(){
        final String username = txtUsername.getText().toString().trim();
        final String passowrd = txtPassword.getText().toString().trim();
        final String status = txtStatus.getText().toString().trim();
        final String email = txtEmail.getText().toString().trim();
        final String type = requestTypeOnly;

        class UbahLoginSiswa extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FormLoginSiswaActivity.this, "Mengubah","Mohon Tunggu..",false,false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_ID_LOGINSISWA, id);
                params.put(konfigurasi.KEY_USERNAME, username);
                params.put(konfigurasi.KEY_PASSWORD, passowrd);
                params.put(konfigurasi.KEY_STATUS, status);
                params.put(konfigurasi.KEY_EMAIL, email);
                params.put(konfigurasi.KEY_TYPE, type);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_UPDATE, params);

                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(FormLoginSiswaActivity.this, s, Toast.LENGTH_SHORT).show();

                // kembalikan ke MainActivity dengan memabawa intent
                Intent intent = new Intent(FormLoginSiswaActivity.this, LoginSiswaActivity.class);
                intent.putExtra("requestTypeOnly", requestTypeOnly);
                intent.putExtra("requestTypeWithId", requestTypeWithId);
                startActivity(intent);
            }
        }

        UbahLoginSiswa uls = new UbahLoginSiswa();
        uls.execute();
    }

    public void hapusLoginSiswa(){
        class HapusLoginSiswa extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FormLoginSiswaActivity.this, "Menghapus", "Mohon tunggu..", false, false);
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
                Toast.makeText(FormLoginSiswaActivity.this, s, Toast.LENGTH_SHORT).show();

                // kembalikan ke MainActivity dengan memabawa intent
                Intent intent = new Intent(FormLoginSiswaActivity.this, LoginSiswaActivity.class);
                intent.putExtra("requestTypeOnly", requestTypeOnly);
                intent.putExtra("requestTypeWithId", requestTypeWithId);
                startActivity(intent);
            }
        }

        HapusLoginSiswa hls = new HapusLoginSiswa();
        hls.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == btnInput){
            String txtbutton = btnInput.getText().toString().trim();
            if(txtbutton.equals("Tambah")){
                tambahLoginSiswa();
            }else if(txtbutton.equals("Ubah")){
                ubahLoginSiswa();
            }
        }else if(v == btnHapus){
            hapusLoginSiswa();
        }else if(v == cbShowPass) {
            if(cbShowPass.isChecked()){
                //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan
                txtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                //Jika tidak, maka password akan di sembuyikan
                txtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

}
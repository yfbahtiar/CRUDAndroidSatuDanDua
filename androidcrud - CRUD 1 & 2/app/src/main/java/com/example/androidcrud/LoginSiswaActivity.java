package com.example.androidcrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.androidcrud.config.konfigurasi;
import com.example.androidcrud.modul.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginSiswaActivity extends AppCompatActivity {

    ListView listView;

    public String JSON_STRING, requestTypeOnly, requestTypeWithId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_siswa);

        listView = (ListView)findViewById(R.id.listView);

        getLoginSiswaAll();

        // get intent from HomeActivity
        Intent intent = getIntent();
        requestTypeOnly = intent.getStringExtra("requestTypeOnly");
        requestTypeWithId = intent.getStringExtra("requestTypeWithId");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(LoginSiswaActivity.this, FormLoginSiswaActivity.class);
                HashMap<String, String> map = (HashMap)parent.getItemAtPosition(position);
                //buat nama var dan isi lalu lemmpar ke FormLoginActivity
                String siswaid = map.get(konfigurasi.KEY_ID_LOGINSISWA).toString();
                i.putExtra(konfigurasi.KEY_ID_LOGINSISWA,siswaid);
                i.putExtra("requestTypeOnly", requestTypeOnly);
                i.putExtra("requestTypeWithId", requestTypeWithId);
                startActivity(i);
            }
        });

    }

    public void getLoginSiswaAll(){
        class GetLoginSiswaAll extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginSiswaActivity.this, "Memuat", "Mohon tunggu..", false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {

                // jalankan requestHandler with requestOnly "login_siswa"
                RequestHandler rh = new RequestHandler();
                String res = rh.sendGetRequest(konfigurasi.URL_SHOWALL, requestTypeOnly);

                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                System.out.println(s);
                tampilLoginSiswaAll();
            }
        }

        GetLoginSiswaAll gs = new GetLoginSiswaAll();
        gs.execute();
    }

    public void tampilLoginSiswaAll(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try{
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            for (int i=0;i<result.length();i++){
                JSONObject c = result.getJSONObject(i);
                String id = c.getString(konfigurasi.KEY_ID_LOGINSISWA);
                String username = c.getString(konfigurasi.KEY_USERNAME);
                String email = c.getString(konfigurasi.KEY_EMAIL);

                HashMap<String, String> loginSiswa = new HashMap<>();
                loginSiswa.put(konfigurasi.KEY_ID_LOGINSISWA, id);
                loginSiswa.put(konfigurasi.KEY_USERNAME, username);
                loginSiswa.put(konfigurasi.KEY_EMAIL, email);
                list.add(loginSiswa);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(LoginSiswaActivity.this, list, R.layout.list_login_siswa,
                new String[]{konfigurasi.KEY_ID_LOGINSISWA, konfigurasi.KEY_USERNAME, konfigurasi.KEY_EMAIL},
                new int[]{R.id.txtId,R.id.txtUsername,R.id.txtEmail});

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menudash, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.menuAdd:
                Intent i = new Intent(LoginSiswaActivity.this, FormLoginSiswaActivity.class);
                startActivity(i);
                break;
            case R.id.menuHome:
                Intent h = new Intent(LoginSiswaActivity.this, HomeActivity.class);
                startActivity(h);
        }

        return super.onOptionsItemSelected(item);
    }

}
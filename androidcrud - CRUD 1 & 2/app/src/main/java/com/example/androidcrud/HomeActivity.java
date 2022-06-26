package com.example.androidcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    private CardView evtClickListSiswa, evtClickLoginSiswa;
    private String requestTypeOnly, requestTypeWithId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // defining cards
        initView();
        // adds click listener to the cards
        setData();
    }

    public void initView() {
        evtClickListSiswa = (CardView) findViewById(R.id.evtClickListSiswa);
        evtClickLoginSiswa = (CardView) findViewById(R.id.evtClickLoginSiswa);
    }

    public void setData() {
        // buat event ketika card view siswa di klik
        evtClickListSiswa.setOnClickListener(view -> {
            // set data to send with intent
            requestTypeOnly = "siswa";
            requestTypeWithId = "siswa&id=";
            // action
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.putExtra("requestTypeOnly", requestTypeOnly);
            intent.putExtra("requestTypeWithId", requestTypeWithId);
            startActivity(intent);
        });

        // buat event ketika card view login siswa di klik
        evtClickLoginSiswa.setOnClickListener(view -> {
            // set data to send with intent
            requestTypeOnly = "login_siswa";
            requestTypeWithId = "login_siswa&id=";
            // action
            Intent intent = new Intent(HomeActivity.this, LoginSiswaActivity.class);
            intent.putExtra("requestTypeOnly", requestTypeOnly);
            intent.putExtra("requestTypeWithId", requestTypeWithId);
            startActivity(intent);
        });
    }
}
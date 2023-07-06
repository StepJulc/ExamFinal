package com.upn.Bardales.Julcamoro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    EditText nameDuelista;
    Button btRegistro;
    Button btListas;
    Button btSincronizar;

    Retrofit mRetrofit;

    int cont;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mRetrofit = RetrofitBuilder.build();

        nameDuelista = findViewById(R.id.nameDuelista);
        btRegistro = findViewById(R.id.btRegistro);
        btListas = findViewById(R.id.btListas);
        btSincronizar = findViewById(R.id.btSincronizar);


    }
}
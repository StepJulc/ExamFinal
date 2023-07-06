package com.upn.Bardales.Julcamoro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.upn.Bardales.Julcamoro.db.AppDatabase;
import com.upn.Bardales.Julcamoro.entities.duelista;
import com.upn.Bardales.Julcamoro.repositories.DuelistasRepository;
import com.upn.Bardales.Julcamoro.services.duelistaService;
import com.upn.Bardales.Julcamoro.utils.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    EditText nombreDuelista;
    Button btRegistro;
    Button btListas;
    Retrofit mRetrofit;
    int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRetrofit = RetrofitBuilder.build();

        nombreDuelista = findViewById(R.id.nombreDuelista);
        btRegistro = findViewById(R.id.btRegistro);
        btListas = findViewById(R.id.btListas);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        DuelistasRepository repositoryD = db.DuelistaRepository();


        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombreDuelista.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Llenar Datos", Toast.LENGTH_SHORT).show();
                } else {
                    duelista duelista = new duelista();
                    duelista.nameDuelista = nombreDuelista.getText().toString();
                    duelista.sincronizadoDuelista = false;
                    repositoryD.createDuelista(duelista);
                    nombreDuelista.setText("");
                    Log.i("MAIN_APP: Guarda en DB", new Gson().toJson(duelista));
                }

            }
        });

        btListas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        duelistaService serviceD = mRetrofit.create(duelistaService.class);

        if (isNetworkConnected()) {
            List<duelista> SinSicroDuelistas = repositoryD.searchDuelista(false);
            for (duelista duelista :SinSicroDuelistas) {
                Log.d("MAIN_APP: DB SSincro", new Gson().toJson(duelista));
                duelista.sincronizadoDuelista = true;
                repositoryD.updated(duelista);
                //*****SINCRO*************************
                SincronizacionDuelista(serviceD,duelista);

            }
            List<duelista> EliminarBDDuelista= repositoryD.getAllDuelista();
            downloadingMockAPIDuelista(serviceD,repositoryD,EliminarBDDuelista);

            Toast.makeText(getBaseContext(), "SINCRONIZADO", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getBaseContext(), "NO HAY INTERNET", Toast.LENGTH_SHORT).show();

        }
    }

    private void SincronizacionDuelista(duelistaService duelistaService, duelista duelista){
        Call<duelista> call= duelistaService.create(duelista);
        call.enqueue(new Callback<duelista>() {
            @Override
            public void onResponse(Call<duelista> call, Response<duelista> response) {
                if (response.isSuccessful()) {
                    duelista data = response.body();
                    Log.i("MAIN_APP: MockAPI", new Gson().toJson(data));
                }
            }

            @Override
            public void onFailure(Call<duelista> call, Throwable t) {

            }
        });




    }
    private void downloadingMockAPIDuelista(duelistaService duelistaService,DuelistasRepository duelistaRepository , List<duelista> EliminarDuelista){
        //***Eleminar datos de BD
        duelistaRepository.deleteList(EliminarDuelista);
        //Cargar datos de MockAPI
        Call<List<duelista>> call = duelistaService.getAllUser();
        call.enqueue(new Callback<List<duelista>>() {
            @Override
            public void onResponse(Call<List<duelista>> call, Response<List<duelista>> response) {
                List<duelista> data = response.body();
                Log.i("MAIN_APP", new Gson().toJson(data));
                for (duelista cuenta : data) {
                    duelistaRepository.createDuelista(cuenta);
                }
            }

            @Override
            public void onFailure(Call<List<duelista>> call, Throwable t) {

            }
        });
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}

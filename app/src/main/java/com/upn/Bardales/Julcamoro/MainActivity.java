package com.upn.Bardales.Julcamoro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.upn.Bardales.Julcamoro.db.AppDatabase;
import com.upn.Bardales.Julcamoro.entities.Duelista;
import com.upn.Bardales.Julcamoro.repositories.DuelistasRepository;
import com.upn.Bardales.Julcamoro.services.DuelistaService;
import com.upn.Bardales.Julcamoro.utils.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    EditText nombreDuelista;
    Button btRegistro;
    Button btListas;
    Retrofit mRetrofit;
    int con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRetrofit = RetrofitBuilder.build();

        nombreDuelista = findViewById(R.id.nombreDuelista);
        btRegistro = findViewById(R.id.btRegistro);
        btListas = findViewById(R.id.btListas);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        DuelistasRepository repositoryD = db.duelistasRepository();


        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombreDuelista.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Llenar Datos", Toast.LENGTH_SHORT).show();
                } else {
                    Duelista duelista = new Duelista();
                    duelista.nameDuelista = nombreDuelista.getText().toString();
                    duelista.sincronizadoDuelista = false;
                    repositoryD.createDuelista(duelista);
                    nombreDuelista.setText("");
                    Log.i("MAIN_APP: Guarda en DB", new Gson().toJson(duelista));
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        btListas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DuelistaListActivity.class);
                startActivity(intent);
            }
        });

        DuelistaService serviceD = mRetrofit.create(DuelistaService.class);

        if (isNetworkConnected()) {
            List<Duelista> sinSicroDuelistas = repositoryD.searchDuelista(false);
            for (Duelista duelista : sinSicroDuelistas) {
                Log.d("MAIN_APP: DB SSincro", new Gson().toJson(duelista));
                duelista.sincronizadoDuelista = true;
                repositoryD.updateduelista(duelista);

                SincronizacionDuelista(serviceD,duelista);

            }
            List<Duelista> eliminarBDDuelista = repositoryD.getAllDuelista();
            downloadingMockAPIDuelista(serviceD,repositoryD, eliminarBDDuelista);

            Toast.makeText(getBaseContext(), "SINCRONIZADO", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getBaseContext(), "NO HAY INTERNET", Toast.LENGTH_SHORT).show();

        }
    }

    private void SincronizacionDuelista(DuelistaService duelistaService, Duelista duelista){
        Call<Duelista> call= duelistaService.create(duelista);
        call.enqueue(new Callback<Duelista>() {
            @Override
            public void onResponse(Call<Duelista> call, Response<Duelista> response) {
                if (response.isSuccessful()) {
                    Duelista data = response.body();
                    Log.i("MAIN_APP: MockAPI", new Gson().toJson(data));
                }
            }

            @Override
            public void onFailure(Call<Duelista> call, Throwable t) {

            }
        });




    }
    private void downloadingMockAPIDuelista(DuelistaService duelistaService,DuelistasRepository duelistaRepository , List<Duelista> eliminarDuelista){

        duelistaRepository.deleteList(eliminarDuelista);

        Call<List<Duelista>> call = duelistaService.getAllUser();
        call.enqueue(new Callback<List<Duelista>>() {
            @Override
            public void onResponse(Call<List<Duelista>> call, Response<List<Duelista>> response) {
                List<Duelista> data = response.body();
                Log.i("MAIN_APP", new Gson().toJson(data));
                for (Duelista duelista : data) {
                    duelistaRepository.createDuelista(duelista);
                }
            }

            @Override
            public void onFailure(Call<List<Duelista>> call, Throwable t) {

            }
        });
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}


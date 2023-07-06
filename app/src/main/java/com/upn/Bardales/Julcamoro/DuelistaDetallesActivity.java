package com.upn.Bardales.Julcamoro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.upn.Bardales.Julcamoro.db.AppDatabase;
import com.upn.Bardales.Julcamoro.entities.Cartas;
import com.upn.Bardales.Julcamoro.entities.Duelista;
import com.upn.Bardales.Julcamoro.repositories.CartasRepository;
import com.upn.Bardales.Julcamoro.repositories.DuelistasRepository;
import com.upn.Bardales.Julcamoro.services.CartaService;
import com.upn.Bardales.Julcamoro.utils.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DuelistaDetallesActivity extends AppCompatActivity {
    TextView tvNombre;
    TextView tvNumero;
    Button btRegistros;
    Button btVer;

    Retrofit mRetrofit;

    String urlImage = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duelista_detalles);


        mRetrofit = RetrofitBuilder.build();

        tvNombre  = findViewById(R.id.tvNombre);
        tvNumero = findViewById(R.id.tvNumero);
        btRegistros  = findViewById(R.id.btRegistros);
        btVer     = findViewById(R.id.btVer);


        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        DuelistasRepository repositoryD = db.duelistasRepository();

        CartasRepository repositoryC = db.cartaRepository();

        int idObtener;
        idObtener = getIntent().getIntExtra("id",0);
        Log.d("APP_MAIN: idRec", String.valueOf(idObtener));


        Duelista duelista = repositoryD.searchDuelistaID(idObtener);

        tvNombre.setText(duelista.nameDuelista);
        tvNumero.setText(String.valueOf(duelista.id));






        btRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CartaService serviceM = mRetrofit.create(CartaService.class);

        if (isNetworkConnected()) {
            List<Cartas> SinSincroCarta = repositoryC.searchCarta(false);
            for (Cartas carta : SinSincroCarta) {
                Log.d("MAIN_APP: DB S", new Gson().toJson(carta));
                carta.sincronizadoCarta = true;


                repositoryC.updateCartas(carta);

                SincronizacionCarta(serviceM,carta);
            }

            Toast.makeText(getBaseContext(), "SINCRO", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getBaseContext(), "SIN INTERNET", Toast.LENGTH_SHORT).show();

        }

    }


    private void SincronizacionCarta(CartaService movimientoService, Cartas carta) {
        Call<Cartas> call = movimientoService.create(carta);
        call.enqueue(new Callback<Cartas>() {
            @Override
            public void onResponse(Call<Cartas> call, Response<Cartas> response) {
                if(response.isSuccessful()){
                    Cartas data = response.body();
                    Log.i("MAIN_APP: MovMockAPI", new Gson().toJson(data));
                }
            }

            @Override
            public void onFailure(Call<Cartas> call, Throwable t) {

            }
        });
    }
    private void downloadingMockAPICarta(CartaService cartaService, CartasRepository cartaRepository) {


        Call<List<Cartas>> call = cartaService.getAllUser();
        call.enqueue(new Callback<List<Cartas>>() {
            @Override
            public void onResponse(Call<List<Cartas>> call, Response<List<Cartas>> response) {
                List<Cartas> data = response.body();
                Log.i("MAIN_APP", new Gson().toJson(data));

                for (Cartas carta : data) {
                    cartaRepository.createCarta(carta);

                }
            }

            @Override
            public void onFailure(Call<List<Cartas>> call, Throwable t) {

            }
        });

    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
package com.upn.Bardales.Julcamoro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.upn.Bardales.Julcamoro.db.AppDatabase;
import com.upn.Bardales.Julcamoro.entities.Duelista;
import com.upn.Bardales.Julcamoro.repositories.CartasRepository;
import com.upn.Bardales.Julcamoro.repositories.DuelistasRepository;
import com.upn.Bardales.Julcamoro.services.CartaService;
import com.upn.Bardales.Julcamoro.utils.RetrofitBuilder;

import retrofit2.Retrofit;

public class DuelistaDetallesActivity extends AppCompatActivity {
    TextView tvNombre;
    TextView tvNumero;
    Button btRegistros;
    Button btVer;

    Retrofit mRetrofit;

    String urlImage = "";

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duelista_detalles);


        mRetrofit = RetrofitBuilder.build();

        tvNombre = findViewById(R.id.tvNombre);
        tvNumero = findViewById(R.id.tvNumero);
        btRegistros = findViewById(R.id.btRegistros);
        btVer = findViewById(R.id.btVer);


        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        DuelistasRepository repositoryD = db.duelistasRepository();

        CartasRepository repositoryC = db.cartaRepository();

        int idObtener;
        idObtener = getIntent().getIntExtra("id", 0);
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

    }
}
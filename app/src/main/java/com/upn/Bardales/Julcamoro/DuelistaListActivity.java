package com.upn.Bardales.Julcamoro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.upn.Bardales.Julcamoro.adapters.DuelistaAdapter;
import com.upn.Bardales.Julcamoro.db.AppDatabase;
import com.upn.Bardales.Julcamoro.entities.Duelista;
import com.upn.Bardales.Julcamoro.repositories.DuelistasRepository;

import java.util.List;

public class DuelistaListActivity extends AppCompatActivity {

    RecyclerView rvListaDu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duelista_list);

        Log.d("APP_MAIN", "List");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvListaDu = findViewById(R.id.rvListaDu);
        rvListaDu.setLayoutManager(layoutManager);

        LoadingDBCuenta();

    }

    private void LoadingDBCuenta() {
        AppDatabase db = AppDatabase.getInstance(this);
        DuelistasRepository repository = db.duelistasRepository();
        List<Duelista> mdataDuelista = repository.getAllDuelista();

        DuelistaAdapter mAdapter = new DuelistaAdapter(mdataDuelista);
        rvListaDu.setAdapter(mAdapter);
        Log.i("MAIN_APP: DB", new Gson().toJson(mdataDuelista));
        Toast.makeText(getBaseContext(), "MOSTRANDO DATOS", Toast.LENGTH_SHORT).show();

    }
}
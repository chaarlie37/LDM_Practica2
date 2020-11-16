package com.ldm.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PartidasActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyPartidaRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partidas);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_partidas);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyPartidaRecyclerViewAdapter(MiRoom.getMiRoom(getApplicationContext()).partidaDAO().findAllPartidas());
        recyclerView.setAdapter(adapter);



    }


    public void atras(View view){
        // Con los flags del Intent "limpiamos" la pila de Actividades, para que una vez se vuelva a la pantalla de inicio, si el usuario
        // pulsa en el botón de ATRÁS se salga de la aplicación en vez de volver a la pantalla de resultados
        Intent mainIntent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(mainIntent);
    }


}

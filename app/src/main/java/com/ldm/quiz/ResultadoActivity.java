package com.ldm.quiz;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        // Si el usuario pulsa el botón de ATRÁS, que le lleve a la pantalla de inicio y no a la
        // Activity del quiz, el cual ya ha jugado y no se le debería permitir volver
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                volverAlInicio(null);
            }
        };
        getOnBackPressedDispatcher().addCallback(callback);

        // Se muestra por pantalla el resultado de la partida.
        int puntos = getIntent().getIntExtra("puntos", 0);
        TextView resultado = (TextView) findViewById(R.id.resultado);
        resultado.setText(getResources().getQuantityString(R.plurals.msg_final, puntos, puntos));
    }

    public void volverAlInicio(View view){
        // Con los flags del Intent "limpiamos" la pila de Actividades, para que una vez se vuelva a la pantalla de inicio, si el usuario
        // pulsa en el botón de ATRÁS se salga de la aplicación en vez de volver a la pantalla de resultados
        Intent mainIntent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(mainIntent);
    }

}

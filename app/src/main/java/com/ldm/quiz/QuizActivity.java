package com.ldm.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import androidx.gridlayout.widget.GridLayout;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    protected int puntos;

    protected List<Pregunta> preguntas;
    protected Pregunta preguntaActual;

    protected int num_pregunta;

    protected RadioGroup radioGroup;
    protected List<RadioButton> radioButtons;
    protected List<ImageButton> imageButtons;

    protected ProgressBar progressBar;
    protected TextView texto_puntos;

    protected TextView texto_pregunta;
    protected ImageView imagen_pregunta;

    protected GridLayout layoutRespuestasImagen;

    protected MiRoom miRoom;

    protected Partida partida;
    protected List<Boolean> respuestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Botones de las respuestas
        radioButtons = new ArrayList<>();
        radioButtons.add((RadioButton) findViewById(R.id.respuesta1));
        radioButtons.add((RadioButton) findViewById(R.id.respuesta2));
        radioButtons.add((RadioButton) findViewById(R.id.respuesta3));
        radioButtons.add((RadioButton) findViewById(R.id.respuesta4));
        radioGroup = findViewById(R.id.groupRespuestas);

        imageButtons = new ArrayList<>();
        imageButtons.add((ImageButton) findViewById(R.id.respuesta_imagen_1));
        imageButtons.add((ImageButton) findViewById(R.id.respuesta_imagen_2));
        imageButtons.add((ImageButton) findViewById(R.id.respuesta_imagen_3));
        imageButtons.add((ImageButton) findViewById(R.id.respuesta_imagen_4));

        // Texto e imagen de la pregunta
        texto_pregunta = (TextView) findViewById(R.id.pregunta);
        imagen_pregunta = (ImageView) findViewById(R.id.imagen_pregunta);

        // Layout donde se encuentran las respuestas de tipo imagen
        layoutRespuestasImagen = (GridLayout) findViewById(R.id.layout_respuestas_imagen);

        // Barra que visualiza el progreso del juego
        progressBar = findViewById(R.id.progressBar);

        // Texto que visualiza los puntos que lleva el jugador
        texto_puntos = findViewById(R.id.puntos_text);

        // Número de pregunta actual. Empieza en -1 ya que el método cambiarPregunta() lo incrementa
        num_pregunta = -1;

        // Generación de las preguntas
        preguntas = new ArrayList<>();

        preguntas.add(new PreguntaTexto("¿A qué marca pertenece este logotipo?", "Coca Cola", "Canon", "Pepsi Cola", "Carlsberg", R.drawable.cocacola));
        preguntas.add(new PreguntaTexto("¿A qué marca pertenece este logotipo?", "Amazon", "Amazonia", "AliExpress", "SmileShop", R.drawable.amazon));
        preguntas.add(new PreguntaTexto("¿A qué marca pertenece este logotipo?", "Burger King", "McDonalds", "Pizza Hut", "Goiko Grill", R.drawable.burgerking));
        preguntas.add(new PreguntaTexto("¿A qué marca pertenece este logotipo?", "Intel", "AMD", "Nvidia", "Xiaomi", R.drawable.intel));
        preguntas.add(new PreguntaImagenes("¿Cuál es el logotipo de Subaru?", R.drawable.subaru, R.drawable.mercedes, R.drawable.toyota, R.drawable.huyndai));




/*

        //MiRoom.getMiRoom(getApplicationContext()).preguntaTextoDAO().deleteAllPreguntaTexto();
        //MiRoom.getMiRoom(getApplicationContext()).preguntaImagenesDAO().deleteAllPreguntaImagenes();


        for (Pregunta preguntaTexto : preguntas){
            //miRoom.insertPreguntaTexto((PreguntaTexto) preguntaTexto);
            //MiRoom.getMiRoom(getApplicationContext()).insertPreguntaTexto((PreguntaTexto) preguntaTexto);
            MiRoom.getMiRoom(getApplicationContext()).preguntaTextoDAO().insertPreguntaTexto((PreguntaTexto) preguntaTexto);
        }

        //MiRoom.getMiRoom(getApplicationContext()).preguntaImagenesDAO().insertPreguntaImagenes((PreguntaImagenes) new PreguntaImagenes("¿Cuál es el logotipo de Subaru?", R.drawable.subaru, R.drawable.mercedes, R.drawable.toyota, R.drawable.huyndai));

        List<PreguntaTexto> preguntasTexto = MiRoom.getMiRoom(getApplicationContext()).preguntaTextoDAO().findAllPreguntaTexto();
        List<PreguntaImagenes> preguntasImagenes = MiRoom.getMiRoom(getApplicationContext()).preguntaImagenesDAO().findAllPreguntaImagenes();
        preguntas.addAll(preguntasTexto);
        preguntas.addAll(preguntasImagenes);


*/

        partida = new Partida();
        respuestas = new ArrayList<>();


        cambiarPregunta();
    }


    // Muestra por pantalla la siguiente pregunta de la lista. Si ya no quedan, el juego finaliza.
    public void cambiarPregunta(){
        num_pregunta++;
        progressBar.setProgress(((num_pregunta) * 100) / 5, true);
        if (puntos < 0)
            puntos = 0;
        texto_puntos.setText(getString(R.string.puntos_acumulados, puntos));

        // Si la pregunta actual es >4, finaliza el quiz
        if (num_pregunta > 4){
            terminarQuiz();
        }
        else {
            // Se genera un índice aleatorio para que las preguntas salgan en orden aleatorio cada vez que se juega
            int i = (int) (Math.random() * preguntas.size());
            preguntaActual = preguntas.get(i);
            preguntas.remove(i);
            Button botonSiguiente = findViewById(R.id.btn_siguiente);

            // Si la pregunta actual es de tipo respuesta de imágenes
            if (preguntaActual.getClass() == PreguntaImagenes.class){
                PreguntaImagenes preguntaImagenes = (PreguntaImagenes) preguntaActual;
                botonSiguiente.setVisibility(View.GONE);
                layoutRespuestasImagen.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.GONE);
                imagen_pregunta.setVisibility(View.GONE);
                texto_pregunta.setText(preguntaImagenes.getPregunta());
                List<Integer> respuestasImagenes = new ArrayList<>(preguntaImagenes.getRespuestas());
                // Se muestran las respuestas en orden aleatorio
                for (ImageButton imageButton : imageButtons){
                    int indice = (int) (Math.random() * respuestasImagenes.size());
                    imageButton.setImageResource(respuestasImagenes.get(indice));
                    // Se guarda en el Tag de los ImageButtons la respuesta en texto, para luego consultarla, ya que
                    // no existe un método getImageResource y ver de qué imagen se trata
                    imageButton.setTag(respuestasImagenes.get(indice));
                    respuestasImagenes.remove(indice);
                }
            }
            // Si la pregunta actual es de tipo respuesta de texto
            else if (preguntaActual.getClass() == PreguntaTexto.class){
                layoutRespuestasImagen.setVisibility(View.GONE);
                radioGroup.setVisibility(View.VISIBLE);
                imagen_pregunta.setVisibility(View.VISIBLE);
                botonSiguiente.setVisibility(View.VISIBLE);
                PreguntaTexto preguntaTexto = (PreguntaTexto) preguntaActual;
                List<String> respuestas = new ArrayList<>(preguntaTexto.getRespuestas());
                texto_pregunta.setText(preguntaTexto.getPregunta());
                // Se muestran las respuestas en orden aleatorio
                for (RadioButton boton : radioButtons){
                    int indice = (int) (Math.random() * respuestas.size());
                    boton.setText(respuestas.get(indice));
                    respuestas.remove(indice);
                }
                imagen_pregunta.setImageResource(preguntaTexto.getImagen());
            }
        }



    }

    public void comprobarRespuesta(View view){
        if (preguntaActual.getClass() == PreguntaImagenes.class){
            /*
                En este tipo de preguntas, la mecánica es la siguiente:
                   El usuario responde pulsando en la respuesta correspondiente. El fondo de la imagen
                   se colorea en verde o rojo en función de si la respuesta es correcta o incorrecta,
                   y automáticamente se pasa a la siguiente pregunta tras un delay de 1 segundo para que
                   el usuario vea el resultado de su respuesta.
                 */
            final ImageButton imageButton = (ImageButton) view;
            PreguntaImagenes preguntaImagenes = (PreguntaImagenes) preguntaActual;
            boolean respuestaCorrecta = ((Integer) imageButton.getTag()).equals(preguntaImagenes.getRespuestaCorrecta());
            if (respuestaCorrecta){
                imageButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.respuestaCorrecta)));
                Toast.makeText(this, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show();
                puntos += 3;
                respuestas.add(true);
                // Tras el delay de 1 segundo, se cambia la pregunta
                new CountDownTimer(1000, 1000) {
                    public void onFinish() {
                        cambiarPregunta();
                    }
                    public void onTick(long millisUntilFinished) {}
                }.start();
            }
            else {
                imageButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.respuestaIncorrecta)));
                // Si se ha fallado aparece un diálogo preguntando al usuario si quiere continuar o reiniciar la partida
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("¡Respuesta incorrecta!");
                builder.setMessage("Has fallado. ¿Quieres continuar o prefieres empezar de nuevo?");
                builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        puntos -= 2;
                        respuestas.add(false);
                        cambiarPregunta();
                    }
                });
                builder.setNegativeButton("Empezar de nuevo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent preguntasIntent = getIntent();
                        finish();
                        startActivity(preguntasIntent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
        else {
            final RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            PreguntaTexto preguntaTexto = (PreguntaTexto) preguntaActual;
            boolean respuestaCorrecta = radioButton.getText().equals(preguntaTexto.getRespuestaCorrecta());
            if (respuestaCorrecta){
                puntos += 3;
                respuestas.add(true);
                Toast.makeText(this, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show();
                radioGroup.clearCheck();
                cambiarPregunta();
            }
            else {
                // Si se ha fallado aparece un diálogo preguntando al usuario si quiere continuar o reiniciar la partida
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("¡Respuesta incorrecta!");
                builder.setMessage("Has fallado. ¿Quieres continuar o prefieres empezar de nuevo?");
                builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        puntos -= 2;
                        respuestas.add(false);
                        radioGroup.clearCheck();
                        cambiarPregunta();
                    }
                });
                builder.setNegativeButton("Empezar de nuevo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent preguntasIntent = getIntent();
                        finish();
                        startActivity(preguntasIntent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

    // Se crea un intent para pasar a la Activity que muestra la puntuación
    public void terminarQuiz(){
        partida = new Partida(respuestas.get(0), respuestas.get(1), respuestas.get(2), respuestas.get(3), respuestas.get(4), puntos);
        MiRoom.getMiRoom(getApplicationContext()).partidaDAO().insertPartida(partida);
        Intent resultadoIntent = new Intent(this, ResultadoActivity.class);
        resultadoIntent.putExtra("puntos", puntos);
        startActivity(resultadoIntent);
    }


}


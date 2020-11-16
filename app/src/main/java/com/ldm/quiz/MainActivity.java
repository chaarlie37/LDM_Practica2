package com.ldm.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    SoundPool soundPool;
    int sonido;

    MediaPlayer mediaPlayer;

    boolean musicaActiva;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonido = soundPool.load(this, R.raw.clic, 1);
        // Música de fondo
        musicaActiva = true;
        mediaPlayer = MediaPlayer.create(this, R.raw.fondo);
        mediaPlayer.setVolume(0.25f, 0.25f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void empezarQuiz(View view){
        sonido();
        Intent preguntasIntent = new Intent(this, QuizActivity.class);
        startActivity(preguntasIntent);
    }

    public void historialPartidas(View view){
        sonido();
        Intent partidasIntent = new Intent(this, PartidasActivity.class);
        startActivity(partidasIntent);
    }

    // reproducir sonido al hacer clic en boton
    public void sonido(){
        soundPool.play(sonido, 1, 1, 1, 0, 0);
    }

    public void toggleMusica(View view){
        if (musicaActiva){
            mediaPlayer.setVolume(0f, 0f);
            ImageButton imageButton = (ImageButton) view;
            imageButton.setImageResource(R.drawable.ic_baseline_volume_off_24);
            musicaActiva = false;
        } else {
            mediaPlayer.setVolume(0.25f, 0.25f);
            ImageButton imageButton = (ImageButton) view;
            imageButton.setImageResource(R.drawable.ic_baseline_volume_up_24);
            musicaActiva = true;
        }
    }


}

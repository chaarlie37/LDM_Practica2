package com.ldm.quiz;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities = {PreguntaTexto.class, PreguntaImagenes.class, Partida.class}, version = 1)
public abstract class MiRoom extends RoomDatabase {

    private static MiRoom INSTANCE;
    public abstract PreguntaImagenesDAO preguntaImagenesDAO();
    public abstract PreguntaTextoDAO preguntaTextoDAO();
    public abstract PartidaDAO partidaDAO();

    public static MiRoom getMiRoom(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MiRoom.class, "datos")
                    .allowMainThreadQueries() // revisar esto!!!!
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    /*
    void insertPreguntaTexto(PreguntaTexto preguntaTexto){
        preguntaTextoDAO().insertPreguntaTexto(preguntaTexto);
    }

    PreguntaTexto findPreguntaTextoById(int id){
        return preguntaTextoDAO().findPreguntaTextoById(id);
    }

    List<PreguntaTexto> findAllPreguntaTexto(){
        return preguntaTextoDAO().findAllPreguntaTexto();
    }

     */



}

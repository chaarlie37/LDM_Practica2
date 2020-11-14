package com.ldm.quiz;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PreguntaTextoDAO {

    @Insert
    void insertPreguntaTexto(PreguntaTexto preguntaTexto);

    @Query("SELECT * FROM preguntatexto WHERE id LIKE :id")
    PreguntaTexto findPreguntaTextoById(int id);

    @Query("SELECT * FROM preguntatexto")
    List<PreguntaTexto> findAllPreguntaTexto();

    @Query("SELECT COUNT(*) FROM preguntatexto")
    int getCountPreguntaTexto();

    @Query("DELETE FROM preguntatexto")
    void deleteAllPreguntaTexto();

}

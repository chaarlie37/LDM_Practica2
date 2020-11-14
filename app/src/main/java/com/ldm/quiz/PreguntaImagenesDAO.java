package com.ldm.quiz;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PreguntaImagenesDAO {
    @Insert
    void insertPreguntaImagenes(PreguntaImagenes preguntaImagenes);

    @Query("SELECT * FROM preguntaimagenes WHERE id LIKE :id")
    PreguntaImagenes findPreguntaImagenesById(int id);

    @Query("SELECT * FROM preguntaimagenes")
    List<PreguntaImagenes> findAllPreguntaImagenes();

    @Query("SELECT COUNT(*) FROM preguntaimagenes")
    int getCountPreguntaImagenes();

    @Query("DELETE FROM preguntaimagenes")
    void deleteAllPreguntaImagenes();

}

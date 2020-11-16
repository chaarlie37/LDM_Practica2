package com.ldm.quiz;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PartidaDAO {
    @Insert
    void insertPartida(Partida partida);

    @Query("SELECT * FROM partida WHERE id LIKE :id")
    Partida findPartidaById(int id);

    @Query("SELECT * FROM partida")
    List<Partida> findAllPartidas();

    @Query("SELECT COUNT(*) FROM partida")
    int getCountPartidas();

    @Query("DELETE FROM partida")
    void deleteAllPartidas();

    @Query("DELETE FROM partida WHERE id LIKE :id")
    void deletePartida(int id);
}

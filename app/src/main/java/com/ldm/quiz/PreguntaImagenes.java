package com.ldm.quiz;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity()
public class PreguntaImagenes extends Pregunta{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "preg")
    private String pregunta;
    @ColumnInfo(name = "r2")
    private int respuesta2;
    @ColumnInfo(name = "r3")
    private int respuesta3;
    @ColumnInfo(name = "r4")
    private int respuesta4;
    @ColumnInfo(name = "respuestaCorrecta")
    private int respuestaCorrecta;

    public PreguntaImagenes(String pregunta, int respuestaCorrecta, int respuesta2, int respuesta3, int respuesta4){
        super(pregunta);
        this.pregunta = super.getPregunta();
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        this.respuesta4 = respuesta4;
    }

    @Override
    public String getPregunta() {
        return pregunta;
    }

    @Override
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }


    public List<Integer> getRespuestas() {
        List<Integer> respuestas = new ArrayList<>();
        respuestas.add(respuestaCorrecta);
        respuestas.add(respuesta2);
        respuestas.add(respuesta3);
        respuestas.add(respuesta4);
        return respuestas;
    }


    public Integer getRespuestaCorrecta() {
        return respuestaCorrecta;
    }


    public void setRespuestaCorrecta(Integer respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(int respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public int getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(int respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public int getRespuesta4() {
        return respuesta4;
    }

    public void setRespuesta4(int respuesta4) {
        this.respuesta4 = respuesta4;
    }

    public void setRespuestaCorrecta(int respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }
}

package com.ldm.quiz;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity()
public class PreguntaTexto extends Pregunta{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "preg")
    private String pregunta;
    @ColumnInfo(name = "r2")
    private String respuesta2;
    @ColumnInfo(name = "r3")
    private String respuesta3;
    @ColumnInfo(name = "r4")
    private String respuesta4;
    @ColumnInfo(name = "rCorrecta")
    private String respuestaCorrecta;
    @ColumnInfo(name = "img")
    private int imagen;


    public PreguntaTexto(String pregunta, String respuestaCorrecta, String respuesta2, String respuesta3, String respuesta4, int imagen){
        super(pregunta);
        this.pregunta = super.getPregunta();
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        this.respuesta4 = respuesta4;
        this.imagen = imagen;
    }


    public String getPregunta() {
        return pregunta;
    }


    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }


    public List<String> getRespuestas() {
        List<String> respuestas = new ArrayList<>();
        respuestas.add(respuestaCorrecta);
        respuestas.add(respuesta2);
        respuestas.add(respuesta3);
        respuestas.add(respuesta4);
        return respuestas;
    }



    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }


    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getImagen() {
        return imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public String getRespuesta4() {
        return respuesta4;
    }

    public void setRespuesta4(String respuesta4) {
        this.respuesta4 = respuesta4;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}

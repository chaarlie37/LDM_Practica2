package com.ldm.quiz;

import java.util.List;

public abstract class Pregunta {
    private String pregunta;

    public Pregunta(String p){
        pregunta = p;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}

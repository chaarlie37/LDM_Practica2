package com.ldm.quiz;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class Partida {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo()
    private boolean p1;
    @ColumnInfo()
    private boolean p2;
    @ColumnInfo()
    private boolean p3;
    @ColumnInfo()
    private boolean p4;
    @ColumnInfo()
    private boolean p5;
    @ColumnInfo()
    private int puntos;

    public Partida(boolean p1, boolean p2, boolean p3, boolean p4, boolean p5, int puntos) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.puntos = puntos;
    }

    public Partida(){};

    public boolean isP1() {
        return p1;
    }

    public void setP1(boolean p1) {
        this.p1 = p1;
    }

    public boolean isP2() {
        return p2;
    }

    public void setP2(boolean p2) {
        this.p2 = p2;
    }

    public boolean isP3() {
        return p3;
    }

    public void setP3(boolean p3) {
        this.p3 = p3;
    }

    public boolean isP4() {
        return p4;
    }

    public void setP4(boolean p4) {
        this.p4 = p4;
    }

    public boolean isP5() {
        return p5;
    }

    public void setP5(boolean p5) {
        this.p5 = p5;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}

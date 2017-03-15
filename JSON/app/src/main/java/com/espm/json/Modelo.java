package com.espm.json;

/**
 * Created by novo on 14/03/2017.
 */

public class Modelo {
    private String modelo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Modelo(int id) {

        this.id = id;
    }

    private int id;

    @Override
    public String toString() {
        return modelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Modelo(String modelo) {

        this.modelo = modelo;
    }
}

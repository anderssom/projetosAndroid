package com.espm.json;

/**
 * Created by novo on 08/03/2017.
 */

public class Marca {
    private int id;
    private String nome;

    @Override
    public String toString() {
        return nome;
    }

    public Marca(int id, String nome) {
        this.id = id;
        this.nome = nome;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }
}

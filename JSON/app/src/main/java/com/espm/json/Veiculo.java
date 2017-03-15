package com.espm.json;

/**
 * Created by novo on 08/03/2017.
 */

public class Veiculo {
    private String nomeCarro;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Veiculo(int id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return nomeCarro;
    }

    public String getNomeCarro() {
        return nomeCarro;
    }

    public void setNomeCarro(String nomeCarro) {
        this.nomeCarro = nomeCarro;
    }

    public Veiculo(String nomeCarro) {

        this.nomeCarro = nomeCarro;
    }
}

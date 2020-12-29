package com.nr.servicios;

public class Factura {
    String Tipo,NumeroF,Valor;
    int Id;


    public Factura(String tipo, String numeroF, int id, String valor) {
        Tipo = tipo;
        NumeroF = numeroF;
        Id = id;
        Valor = valor;

    }
    public Factura() {
    }

    @Override
    public String toString() {
        return "Factura{" +
                "Id=" + Id +
                "Tipo=" + Tipo + '\'' +
                ", NumeroF='" + NumeroF + '\'' +
                ", Valor='" + Valor + '\'' +

                '}';
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getNumeroF() {
        return NumeroF;
    }

    public void setNumeroF(String numeroF) {
        NumeroF = numeroF;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

package com.salesianostriana.dam.MiarmaApi.models;

public enum Privacidad {
    PRIVADO ("PRIVADO"), PUBLICO ("PUBLICO");

    private String valor;

    private Privacidad (String valor) {this.valor = valor;}
    public String getValor() {return valor;}
    public void setValor (String valor) {this.valor = valor;}

}

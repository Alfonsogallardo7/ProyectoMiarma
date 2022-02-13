package com.salesianostriana.dam.MiarmaApi.users.models;

public enum UserRole {
    ADMIN ("ADMIN"), USUARIO ("USUARIO");

    private String valor;

    private UserRole (String valor) {this.valor = valor;}
    public String getValor() {return valor;}
    public void setValor (String valor) {this.valor = valor;}
}

package com.bytebank.modelo;

public class GuardaReferencias {

    int indice = 0;

    Object[] referencia = new Object[10];


    public void agregar( Object elemento) {
        referencia[indice] = elemento;
        indice++;
        System.out.println("Estas guardando "+indice+ " referencias");

    }

    public Object getObject(int indice) {
        return referencia[indice];
    }
}

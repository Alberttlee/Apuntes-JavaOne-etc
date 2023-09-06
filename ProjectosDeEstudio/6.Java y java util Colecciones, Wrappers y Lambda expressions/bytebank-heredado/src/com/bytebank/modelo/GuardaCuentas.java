package com.bytebank.modelo;

public class GuardaCuentas {

    //Crear un objeto para guardar muchas Cuentas
    //Permitirnos agregar cuentas con un metodo
    //GuardaCuentas.adiciona(cuenta);
    //Obtener, removier , etc.

    Cuenta[] cuenta;
    int indice = 0;

    public GuardaCuentas(){
        this.cuenta = new Cuenta[10];
    }
    public void adicionar(Cuenta cc) {  //guardando
        cuenta[indice] = cc;
        indice++;
        System.out.println(indice);
    }


    public Cuenta obtener(int indice){ //obteniendo la cuenta
        return cuenta[indice];
    }
}

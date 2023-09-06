package com.bytebank.modelo;

// gerente extiende de funcionario
public class Gerente extends Funcionario implements Autenticable{

//sobre-escritura de metodo
    public double getBonificacion(){
        System.out.println("Ejecutando desde Model.Gerente");
        return 2000;
    }

    @Override
    public void setClave(String clave) {
    }

    @Override
    public boolean iniciarSesion(String clave) {
        return false;
    }
}

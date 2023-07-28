package com.bytebank.modelo;

public class ControlBonificacion {

    private double suma;

    public double registrarSalario(Funcionario funcionario){ //acepta cualquier objeto que sea del tipo Model.Funcionario
        this.suma = funcionario.getBonificacion() + this.suma;
        System.out.println("Calculo actual " + this.suma);
        return this.suma;
    }

}

package br.com.banco.bytebank.test;

import br.com.banco.bytebank.modelo.Cuenta;
import br.com.banco.bytebank.modelo.CuentaCorriente;
import br.com.banco.bytebank.modelo.SaldoInsuficienteException;

public class TestSacar {

    public static void main(String[] args) {
        Cuenta cuenta = new CuentaCorriente(123, 321);

        cuenta.depositar(200.0);

        try {
            cuenta.retirar(210.0);
        } catch(SaldoInsuficienteException ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }

        System.out.println(cuenta.getSaldo());
    }
}

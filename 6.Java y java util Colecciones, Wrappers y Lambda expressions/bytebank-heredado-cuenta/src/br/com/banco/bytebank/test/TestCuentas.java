package br.com.banco.bytebank.test;

import br.com.banco.bytebank.modelo.*;


public class TestCuentas {

    public static void main(String[] args) throws SaldoInsuficienteException {

        CuentaCorriente cc = new CuentaCorriente(111, 111);
        cc.depositar(100.0);

        CuentaAhorros ca = new CuentaAhorros(222,222);
        ca.depositar(500.0);

        Cuenta geronimo = new CuentaAhorros(123, 152);
        geronimo.depositar(500.0);

        cc.transferir(100.0, ca);
        System.out.println("CC: " + cc.getSaldo());
        System.out.println("CA: " + ca.getSaldo());
        System.out.println("Geronimo: " + geronimo.getSaldo());


    }
}

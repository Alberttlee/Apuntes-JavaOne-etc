package com.bytebank.test;

import com.bytebank.modelo.Cuenta;
import com.bytebank.modelo.CuentaAhorros;
import com.bytebank.modelo.SaldoInsufucienteException;

public class TestCuentaExceptionSaldo {

    public static void main(String[] args) {
        Cuenta cuenta = new CuentaAhorros(123,456);
        cuenta.depositar(210);

        try {
            cuenta.retirar(200);
            cuenta.retirar(20);
        } catch (SaldoInsufucienteException e) {
            e.printStackTrace();
        }

    }
}

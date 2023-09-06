package com.bytebank.test;

import com.bytebank.modelo.Cuenta;

public class CuentaEspecial extends Cuenta {
    @Override
    public void depositar(double valor) {
        super.saldo = 0.0;
    }
}

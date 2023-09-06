package br.com.banco.bytebank.test;

import br.com.banco.bytebank.modelo.Cuenta;
import br.com.banco.bytebank.modelo.CuentaCorriente;

public class TestArrayReferencias {
    public static void main(String[] args) {

        Cuenta[] cuentas = new Cuenta[5];

        CuentaCorriente cc1 = new CuentaCorriente(22,11);
        cuentas[0] = cc1;

        CuentaCorriente cc2 = new CuentaCorriente(22,22);
        cuentas[1] = cc2;

        System.out.println(cuentas[1].getNumero());

        CuentaCorriente ref = (CuentaCorriente) cuentas[0];
        System.out.println(cc2.getNumero());
        System.out.println(ref.getNumero());



    }

}

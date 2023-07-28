package br.com.banco.bytebank.test;

import br.com.banco.bytebank.modelo.CuentaCorriente;


public class TestObject {

    public static void main(String[] args) {
        Object cc = new CuentaCorriente(22,11);
        Object cp = new CuentaCorriente(33,22);

        System.out.println(cc);
        System.out.println(cp);

    }

}

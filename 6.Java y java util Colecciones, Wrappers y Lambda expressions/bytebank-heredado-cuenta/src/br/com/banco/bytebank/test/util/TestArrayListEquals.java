package br.com.banco.bytebank.test.util;

import br.com.banco.bytebank.modelo.Cuenta;
import br.com.banco.bytebank.modelo.CuentaCorriente;

import java.util.ArrayList;

public class TestArrayListEquals {

    public static void main(String[] args) {

        ArrayList<Cuenta> lista = new ArrayList<>();

        Cuenta cc1 = new CuentaCorriente(22,22);
        Cuenta cc2 = new CuentaCorriente(22,22);

        lista.add(cc1);

        boolean existe = lista.contains(cc2);

        System.out.println("Ya existe? " + existe);

        for (Cuenta cuenta : lista) {
            System.out.println(cuenta);
        }

        boolean igual = cc1.equals(cc2);
        System.out.println(igual);






    }

}

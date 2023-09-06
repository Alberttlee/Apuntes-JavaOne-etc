package br.com.banco.bytebank.test.util;

import br.com.banco.bytebank.modelo.Cuenta;
import br.com.banco.bytebank.modelo.CuentaAhorros;
import br.com.banco.bytebank.modelo.CuentaCorriente;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestOrdenacion {

    public static void main(String[] args) {

        Cuenta cc1 = new CuentaCorriente(22, 33);
        cc1.depositar(333.0);

        Cuenta cc2 = new CuentaAhorros(22, 44);
        cc2.depositar(444.0);

        Cuenta cc3 = new CuentaCorriente(22, 11);
        cc3.depositar(111.0);

        Cuenta cc4 = new CuentaAhorros(22, 22);
        cc4.depositar(222.0);

        List<Cuenta> lista = new ArrayList<>();
        lista.add(cc1);
        lista.add(cc2);
        lista.add(cc3);
        lista.add(cc4);

        for (Cuenta cuenta : lista) {
            System.out.println(cuenta);
        }

        lista.sort(new NumeroDeCuentaComparator());

        for (Cuenta cuenta : lista) {
            System.out.println(cuenta);
        }




    }
}
class NumeroDeCuentaComparator implements Comparator<Cuenta> {

    @Override
    public int compare(Cuenta o1, Cuenta o2) {
        return Integer.compare(o1.getNumero(), o2.getNumero());
    }
}
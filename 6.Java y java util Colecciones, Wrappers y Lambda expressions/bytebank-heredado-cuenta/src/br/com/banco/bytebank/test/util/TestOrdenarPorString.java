package br.com.banco.bytebank.test.util;

import br.com.banco.bytebank.modelo.Cliente;
import br.com.banco.bytebank.modelo.Cuenta;
import br.com.banco.bytebank.modelo.CuentaAhorros;
import br.com.banco.bytebank.modelo.CuentaCorriente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestOrdenarPorString {

    public static void main(String[] args) {

        Cuenta cc1 = new CuentaCorriente(22, 33);
        Cliente clienteCC1 = new Cliente();
        clienteCC1.setNombre("Nico");
        cc1.setTitular(clienteCC1);
        cc1.depositar(333.0);

        Cuenta ca2 = new CuentaAhorros(22, 44);
        Cliente clienteCC2 = new Cliente();
        clienteCC2.setNombre("Guilermo");
        ca2.setTitular(clienteCC2);
        ca2.depositar(444.0);

        Cuenta cc3 = new CuentaCorriente(22, 11);
        Cliente clienteCC3 = new Cliente();
        clienteCC3.setNombre("Paulo");
        cc3.setTitular(clienteCC3);
        cc3.depositar(111.0);

        Cuenta ca4 = new CuentaAhorros(22, 22);
        Cliente clienteCC4 = new Cliente();
        clienteCC4.setNombre("Ana");
        ca4.setTitular(clienteCC4);
        ca4.depositar(222.0);

        List<Cuenta> lista = new ArrayList<>();
        lista.add(cc1);
        lista.add(ca2);
        lista.add(cc3);
        lista.add(ca4);

//        lista.sort(new TitularDeCuentasComparator());
//        for (Cuenta cuenta : lista) {
//            System.out.println(cuenta + ", " + cuenta.getTitular().getNombre());
//        }


        Collections.sort(lista);//por saldo orden natural
        for (Cuenta cuenta : lista) {
            System.out.println(cuenta + ", " + cuenta.getTitular().getNombre());
        }



    }

}

class TitularDeCuentasComparator implements Comparator<Cuenta> {


    @Override
    public int compare(Cuenta o1, Cuenta o2) {
        String nombrec1 = o1.getTitular().getNombre();
        String nombrec2 = o2.getTitular().getNombre();
        return nombrec1.compareTo(nombrec2);

    }
}

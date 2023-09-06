package com.bytebank.test;

import com.bytebank.modelo.Cliente;
import com.bytebank.modelo.Cuenta;
import com.bytebank.modelo.CuentaAhorros;
import com.bytebank.modelo.CuentaCorriente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TestLambda {

    public static void main(String[] args) {

        Cuenta cc1 = new CuentaCorriente(62,33);
        Cliente clienteCC1 = new Cliente();
        clienteCC1.setNombre("Diego");
        cc1.setTitular(clienteCC1);
        cc1.depositar(333.0);

        Cuenta cc2 = new CuentaAhorros(32,44);
        Cliente clienteCC2 = new Cliente();
        clienteCC2.setNombre("Renato");
        cc2.setTitular(clienteCC2);
        cc2.depositar(444.0);

        Cuenta cc3 = new CuentaCorriente(22,11);
        Cliente clienteCC3 = new Cliente();
        clienteCC3.setNombre("Liam");
        cc3.setTitular(clienteCC3);
        cc3.depositar(111.0);

        Cuenta cc4 = new CuentaAhorros(2,22);
        Cliente clienteCC4 = new Cliente();
        clienteCC4.setNombre("Noel");
        cc4.setTitular(clienteCC4);
        cc4.depositar(222.0);

        List<Cuenta> lista = new ArrayList<>();
        lista.add(cc1);
        lista.add(cc2);
        lista.add(cc3);
        lista.add(cc4);

        System.out.println("Antes de ordenar");
        for (Cuenta cuenta : lista){
            System.out.println(cuenta);
        }


        //Usando lambdas
        lista.sort((Cuenta o1, Cuenta o2) ->
                Integer.compare(o1.getNumero(), o2.getNumero())
        );

        System.out.println("Despues de ordenar");
        for (Cuenta cuenta : lista){
            System.out.println(cuenta);
        }

        //Forma Antigua
        Collections.sort(lista, (Cuenta o1, Cuenta o2) ->
                o1.getTitular().getNombre().compareTo(o2.getTitular().getNombre())
        );

        System.out.println("Despues de ordenar por nombre titular");
//        for (Cuenta cuenta : lista){
//            System.out.println(cuenta);
//        }

        lista.forEach(cuenta -> System.out.println(cuenta));

        Collections.reverse(lista);

        System.out.println("Despues de ordenar por orden Natural");
        for (Cuenta cuenta : lista){
            System.out.println(cuenta);
        }




    }
}


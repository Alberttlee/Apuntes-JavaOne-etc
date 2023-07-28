package com.bytebank.test;

import com.bytebank.modelo.Cliente;
import com.bytebank.modelo.Cuenta;
import com.bytebank.modelo.CuentaCorriente;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestArrayList {

    public static void main(String[] args) {

        //<> Forzando a que solo acepte un tipo de objeto
//        List<Cliente> listaClientes = new LinkedList<>();

        List<Cuenta> lista = new ArrayList<>();
        // referencia           objeto -> HEAP
        CuentaCorriente cc = new CuentaCorriente(11,22);
        CuentaCorriente cc2 = new CuentaCorriente(13,42);
        CuentaCorriente cc3 = new CuentaCorriente(11,22);

        lista.add(cc);
        lista.add(cc2);

//        Cliente cliente = new Cliente();
//         lista.add(cliente);

        Cuenta obtenerCuenta = lista.get(0);
        System.out.println(obtenerCuenta);

        for (int i= 0 ; i< lista.size(); i++ ) {
            System.out.println(lista.get(i));
        }

        for (Cuenta cuenta : lista){
            System.out.println((cuenta));

        }

        boolean contiene = lista.contains(cc3); //si la lista contiene este elemento (retorna un boolean)
        //por referencia
        if (contiene){
            System.out.println("Si es igual con (equals)");
        } else {
            System.out.println("No contiene");
        }

        //por valores





    }

}

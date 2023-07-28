package com.bytebank.test;

import com.bytebank.modelo.Cliente;
import com.bytebank.modelo.Cuenta;
import com.bytebank.modelo.CuentaAhorros;
import com.bytebank.modelo.CuentaCorriente;

import java.util.Arrays;

public class TestArregloReferencias {

    public static void main(String[] args) {
        //                          [new |cc |null  |null  |null  ]
        Object[] referencias = new Object[5];//nuevo objeto

        CuentaCorriente cc = new CuentaCorriente(23,44);


        referencias[1] = cc; //referencia al objeto de la memoria heap guardado en un array

        Cliente cliente = new Cliente();
        referencias[4] = cliente;

        Cliente obtenido = (Cliente) referencias[4];
        System.out.println(cliente);

        CuentaAhorros ca = new CuentaAhorros(44,55);
        referencias[3] = ca;


        referencias[0] = new CuentaCorriente(11,99); //nuevo objeto
        System.out.println(referencias[0]);//referencia

        //Cast//
        CuentaCorriente cuenta = (CuentaCorriente) referencias[1];
        System.out.println(cuenta);


        for (int i = 0; i < referencias.length; i++) {
            System.out.println(referencias[i]);
        }

        Cliente clienteNormal = new Cliente();
        clienteNormal.setNombre("Flavio");

        Cliente clienteVip = new Cliente();
        clienteVip.setNombre("Romulo");

        Object[] refs = new Object[5];
        refs[0]  = clienteNormal;
        refs[1]  = clienteVip;

        Cliente cv = (Cliente)refs[1];

        System.out.println(cv.getNombre());










    }


}

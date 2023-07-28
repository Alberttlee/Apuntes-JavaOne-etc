package com.bytebank.test;

import com.bytebank.modelo.CuentaCorriente;
import com.bytebank.modelo.GuardaReferencias;

public class TestGuardaReferencias {

    public static void main(String[] args) {

        GuardaReferencias guardaObject = new GuardaReferencias();
        CuentaCorriente cc1 = new CuentaCorriente(11,23);
        guardaObject.agregar(cc1);
        System.out.println(guardaObject.getObject(0));
        int num = 23;
        guardaObject.agregar(num);
        System.out.println(guardaObject.getObject(1));

    }


}

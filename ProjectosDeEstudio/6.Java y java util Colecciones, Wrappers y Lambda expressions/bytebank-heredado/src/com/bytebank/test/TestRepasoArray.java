package com.bytebank.test;

import java.util.ArrayList;
import java.util.List;

public class TestRepasoArray {

    public static void main(String[] args) {

        int[] numeros = new int[10]; //nace con un tamaño fijo

        int numero = 40;
        //Wrapper
//        Integer numeroObjeto = new Integer(40) //DEPRECADO
        Integer numeroObjeto = Integer.valueOf(40); //el valueOf de 40 va a ser mappeado a un objeto a integer


        List<Integer> lista = new ArrayList<>(); //crea la misma estructura que un array, ya tiene metodos directos para interactuar
        //primitivo != object
        lista.add(numero); //autoxing
        lista.add(Integer.valueOf(1));
        lista.add(numeroObjeto);

        //unboxing
        int valorPrimitivo = numeroObjeto;
//        int valorPrimitivo = numeroObjeto.intValue();//del numeroobjeto retorname el int

        byte byteInteger = numeroObjeto.byteValue();
        double doubleInteger = numeroObjeto.doubleValue();
        float floatInteger = numeroObjeto.floatValue();

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println(Integer.SIZE); //BITS
        System.out.println(Integer.BYTES );



    }

}

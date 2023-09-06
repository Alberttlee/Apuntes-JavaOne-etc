package br.com.banco.bytebank.test.util;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class TestWrapperInteger {

    public static void main(String[] args) {

        Integer edadRef = 29; //autounboxing
        int primitivo = new Integer(21); //unboxing

        List<Integer> lista = new ArrayList<>();
        lista.add(edadRef);
        lista.add(primitivo);

        int i1 = lista.get(0);
        Integer i2 = lista.get(1);

        System.out.println(i1);
        System.out.println(i2);


        Integer valorRef =Integer.valueOf(33); //hereda la creacion al metodo valueOf
        int valuePri = valorRef.intValue();//desenvolver tomando el valor primitivo del objeto contenedor


        Integer iParseado1 = Integer.valueOf("42"); //parseando y devolviendo referencia
        int iParseado2 = Integer.parseInt("44"); //parseando y devolviendo primitivo

        System.out.println(iParseado1);
        System.out.println(iParseado2);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println(Integer.SIZE);//bits
        System.out.println(Integer.BYTES);

    }
}

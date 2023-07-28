package com.bytebank.test;

public class TestWrappers {

    public static void main(String[] args) {

        Double numeroDouble = 33.0; //autoboxing
        Boolean verdadero = true; //autoboxing

        Double numeroDouble2 = Double.valueOf(33);
        System.out.println( numeroDouble2);

        String numeroString = "43";
        String numeroString2 = new String("43");

        Double atringToDouble = Double.valueOf(numeroString);
        Integer atringToInteger = Integer.valueOf(numeroString);

        System.out.println(atringToDouble);
        System.out.println(atringToInteger);

        Number numero = Integer.valueOf(5);
        double numeroDoblePrim = numero.doubleValue(); //unboxing

        Boolean falso = Boolean.FALSE;

        String diaComoTexto = "29";
        int dia = Integer.valueOf(diaComoTexto);
        int dia2 = Integer.parseInt(diaComoTexto);



    }

}

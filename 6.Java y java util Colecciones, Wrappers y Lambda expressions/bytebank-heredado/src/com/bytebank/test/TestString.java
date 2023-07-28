package com.bytebank.test;

import com.bytebank.modelo.CuentaAhorros;

public class TestString {

    public static void main(String[] args) {


        String nombre = "Camisa";  //comportamiento exclusivo de String
        //No utilizada en el mundo real
        //String nombre2 = new String("Alura");

        System.out.println("Antes de método " + nombre);

        nombre = nombre.replace("C", "c"); //remplaza
        nombre = nombre.concat(" color rojo pasion"); //concatena
        nombre = nombre.toUpperCase(); //Convierte a Mayuscula
        nombre = nombre.toLowerCase(); //Convierte a minuscula
        System.out.println(nombre.charAt(3));  //devulve el caracter al indice agregado
        char letra = nombre.charAt(5);
        int indice = nombre.indexOf("j"); //devuelve el idice del caracter seleccionado
        System.out.println("Despues de método " + nombre);
        System.out.println("Caracter encontrado: " +  letra);
        System.out.println("Indice encontrado " + indice);
        boolean is = nombre.isBlank();
        System.out.println(is);

        /* CharSequence es una interfaz que implementa String, podemos declarar la variable
        con el tipo de interfaz, pero es raro de ver */
        CharSequence sequence = "Hola bebe";
        System.out.println(sequence);

        System.out.println(nombre.matches("[a-z]?"));


        ////////////
        Object cuenta = new CuentaAhorros(123,150);

        printLine(nombre);
        printLine(letra);
        printLine(indice);
        printLine(new CuentaAhorros(200,300));


    }

    public static void printLine(Object valor){
        System.out.println(valor.toString());
    }


}

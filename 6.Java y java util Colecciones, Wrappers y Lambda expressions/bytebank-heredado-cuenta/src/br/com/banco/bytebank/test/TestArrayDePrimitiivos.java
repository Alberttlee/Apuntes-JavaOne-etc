package br.com.banco.bytebank.test;

public class TestArrayDePrimitiivos {

    public static void main(String[] args) {

        int[] edades = new int[5];

        for (int i = 0; i < edades.length; i++){
            edades[i] = i * i;
        }

        for (int i = 0; i < edades.length; i++){
            System.out.println(edades[i]);
        }

    }

}

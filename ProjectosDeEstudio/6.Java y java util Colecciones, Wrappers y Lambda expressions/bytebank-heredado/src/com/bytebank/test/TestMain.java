package com.bytebank.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {

        


        for  (int i= 0; i < args.length; i++){
            System.out.println(args[i]);
        }


        int edad = 20;
        int edad2 = 15;
        int edad3 = 67;

        //quiero agruparlos en una sola referencia, en un grupo

        //Indices        0   1   2    3   4
        //             [ 0 | 0 | 30 | 0 | 0 ]
        int[] edades = new int[5]; //tamanio del array

        edades[2] = 30; //explicar el indice al que yo quiero acceder o guardar

        System.out.println(edades[2]);
        System.out.println(edades[0]);

        //System.out.println(edades[5]);

        int tamanoArray = edades.length;
        System.out.println(tamanoArray);

        for  (int i= 0; i < tamanoArray; i++){
            System.out.println(edades[i]);
        }

        // [x  |   |   |x  |   |   ]




    }



}

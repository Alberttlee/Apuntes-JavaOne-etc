package Arrays;

import java.util.ArrayList;

public class TestArrayList {

    public static void main(String[] args) {

        ArrayList<Integer> numeros2 = new ArrayList<>();
        numeros2.add(2);
        numeros2.add(3);  //Añadir elementos al Array
        numeros2.add(4);
        numeros2.add(5);

        System.out.println(numeros2.get(2)); //Acceder a elementos del ArrayList


        numeros2.remove(3);//Eliminar elementos

        numeros2.set(0,1); //Cambiar el valor de un elemento

        System.out.println(numeros2.size()); //Obtener el tamaño de un Array

        System.out.println(numeros2.contains(6)); //Saber si la lista contiene un elemento,retorna un boolean

    }
}

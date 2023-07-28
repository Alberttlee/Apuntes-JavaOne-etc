package Arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class TestArray {
    public static void main(String[] args) {

        int[][] matriz = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        imprimirMatriz(matriz);
    }

    public static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }


        System.out.println("-------------");

        int[] numeros = {2,3,4,5,6};
        System.out.println(Arrays.toString(numeros)); //Este método es útil cuando queremos imprimir o mostrar el contenido de un array.


    }
}

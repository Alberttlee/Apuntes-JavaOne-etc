public class TestBucles2 {

    public static void main(String[] args) {
        //Practiquemos el comando break imprimiendo una matriz triangular
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                if (columna > fila) {
                    break;
                }
                System.out.print("*");
            }
            System.out.println();
        }

        //mejorando el codigo
        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna <= fila; columna++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

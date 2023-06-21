public class TestBucles {

    public static void main(String[] args){
        //Escribe un for encadenado que imprima la tabla de multiplicar para cada número
        for (int multiplicador = 1; multiplicador <= 10; multiplicador++) {
            for (int contador = 0; contador <= 10; contador++) {
                System.out.print(multiplicador * contador);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

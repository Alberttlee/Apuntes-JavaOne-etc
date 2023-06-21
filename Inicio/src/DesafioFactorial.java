public class DesafioFactorial {

    public static void main(String[] args) {
        /*
        En este ejercicio opcional, tu desafío es imprimir los factoriales del 1 al 10.

        El factorial de 4! = 1 x 2 x 3 x 4 = 24
        El factorial de 6! = 1 x 2 x 3 x 4 x 5 x 6 = 720
        */

        int factorial = 1;// es importante inicializarlo a 1, ya que multiplicará
        // por ejemplo: el factorial de 10 es:
        // 10*9*8*7*6*5*4*3*2*1
        for (int i = 1; i <= 10; i++) {
            factorial = factorial * i;
            System.out.println("El factorial de " + i + " es: " + factorial);
        }

        //Obtener el los factoriales de los numeros del 1 al 15

        int factorial1 = 1;

        for (int i = 1; i <= 15; i++){
            factorial1 = factorial1 * i;
            System.out.println("El factorial de " + i + " es " + factorial1);
        }


    }
}

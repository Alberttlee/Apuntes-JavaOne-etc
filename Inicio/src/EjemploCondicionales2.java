public class EjemploCondicionales2 {

    public static void main(String[] args) {
        System.out.println("Hello word");

        int edad = 21;
        int cantidadPersonas = 2;

        boolean esPareja = cantidadPersonas > 1;
        boolean puedeEntrar = edad >= 18 && esPareja;

        System.out.println("El valor de la condicion es: " + esPareja);

        if (puedeEntrar) {
            System.out.println("Sea usted bienvenido");
        } else {
            System.out.println("Usted no esta permitido a entrar");
        }
    }
}

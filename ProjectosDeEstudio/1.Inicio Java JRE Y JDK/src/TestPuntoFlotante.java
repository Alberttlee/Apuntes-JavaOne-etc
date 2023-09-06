public class TestPuntoFlotante {

    public static void main(String[] args){

        double salario = 1250.70;
        System.out.println(salario);
        System.out.println("Mi salario es " +salario);

        /*
        int valor = 12.5;             no compila.

        int valor2 = 0.0;             no compila.

        double test = 125.50;         compila

        int valor3 = test;           no compila.
        */

        int divsion  = 5/2;
        System.out.println(divsion);

        double divsion1  = 5/2;      //resultado 2.0
        System.out.println(divsion1);

        double divsion2  = 5/2.0;    //resultado 2.5
        System.out.println(divsion2);

    }
}

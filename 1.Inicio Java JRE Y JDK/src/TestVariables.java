public class TestVariables {

    public static void main(String[] args){
        System.out.println("Hola mundo!!!");
        //Java es fuertemente tipado
        //Enteros
        int edad  = 28;
        System.out.println(edad);

        edad = 47;
        System.out.println(edad);

        edad = 46 + 88;
        System.out.println(edad);

        edad = 46 + 88;
        System.out.println("Mi edad es " + edad); //Concatenamos

        //Tipo double
        double salario = 1250.56;
        System.out.println(salario);

        double edad1 = 28;
        double salarioMitad = salario / 2;
        System.out.println(salarioMitad);


        int division = 1250 / 3; //redondea por el tipo de dato int
        System.out.println(division);


    }
}

package enums;

public class Main {

    public static void main(String[] args) {

        for(Nombres mostrar: Nombres.values()){
            System.out.println("Nonbres de chicas: " + mostrar);
        }


        int numero = 5;

        String numeroTexto = switch (numero){
            case 1 -> "uno";
            case 2 -> "dos";
            case 3 -> "tres";
            case 4 -> "cuatro";
            case 5 -> "cinco";
            default -> "numero no encontrado";
        };

        System.out.println(numeroTexto);

    }


}

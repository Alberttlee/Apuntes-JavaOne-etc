import java.util.Scanner;

public class TestSwitch {

    public static void main(String[] args) {

        Scanner src = new Scanner(System.in);
        System.out.println("Proporciona un numero del 1 al 5");
        int mes = Integer.parseInt(src.nextLine());

        String mumeroTexto = switch (mes){
            case 1 -> "Uno";
            case 2 -> "Dos";
            case 3 -> "Tres";
            case 4 -> "Cuatro";
            case 5 -> "Cinco";
            default -> "No conocido";
        };

    }
}

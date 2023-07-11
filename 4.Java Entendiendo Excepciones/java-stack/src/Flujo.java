public class Flujo {
    public static void main(String[] args) {
        System.out.println("Inicio del main");
        try {
            metodo1();
        } catch (Exception exception) {
            String msg = exception.getMessage();
            System.out.println("Exception " + msg);
            exception.printStackTrace(System.out);
        }
        System.out.println("Fin del main");
    }

    private static void metodo1() {
        System.out.println("Inicio del metodo1");
        metodo2();
        System.out.println("Fin del metodo1");
    }

    private static void metodo2() {
        System.out.println("Inicio del metodo2");
        for(int i = 1; i <= 5; i++) {
            System.out.println(i);
            //int a = i / 0;
        }
        System.out.println("Fin del metodo2");
    }
}

public class FlujoConTratamiento {
    public static void main(String[] args) {
        System.out.println("Inicio del main");
        try {
            metodo1();
        } catch (ArithmeticException | NullPointerException | MiException exception) {
            String msg = exception.getMessage();
            System.out.println("Exception " + msg);
            exception.printStackTrace(System.out);
        }
        System.out.println("Fin del main");
    }

    private static void metodo1() throws MiException{
        System.out.println("Inicio del metodo1");
        metodo2();
        System.out.println("Fin del metodo1");
    }

    private static void metodo2() throws MiException{
        System.out.println("Inicio del metodo2");
        throw new MiException("Surgio un error");
        // System.out.println("Fin del metodo2");
    }
}

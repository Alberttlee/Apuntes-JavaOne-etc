public class TestCuentaConExceptionChecked {

    public static void main(String[] args) {

        Cuenta c = new Cuenta();
        try {
            c.depositar();
        } catch (MiException ex){
            System.out.println("Tratamiento....");
        }

    }
}

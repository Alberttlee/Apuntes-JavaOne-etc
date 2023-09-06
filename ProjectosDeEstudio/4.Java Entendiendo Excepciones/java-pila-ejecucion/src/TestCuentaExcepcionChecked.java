public class TestCuentaExcepcionChecked {

    public static void main(String[] args) {

        Cuenta cuenta = new Cuenta();

        try {
            cuenta.deposita();
        } catch (MiExcepcion e) {
            e.printStackTrace(System.out);
        }


    }
}

public class TestReferencia3 {

    public static void main(String[] args) {
        Cuenta cuentaDeDiego = new Cuenta(2);
        System.out.println(cuentaDeDiego.getTitular().getNombre());
        cuentaDeDiego.getTitular().setNombre("Albertito");
        System.out.println(cuentaDeDiego.getTitular().getNombre());
    }
}

public class PruebaEncapsulamiento {

    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta(2);
        Cliente cliente = new Cliente();
        cliente.setNombre("Diego");
        cliente.setDocumento("fsaf654646");

        cuenta.setTitular(cliente);

        Cliente titular = cuenta.getTitular();

        System.out.println(cuenta);
        System.out.println(cliente);
        System.out.println(cuenta.getTitular());
        System.out.println(titular);
    }
}

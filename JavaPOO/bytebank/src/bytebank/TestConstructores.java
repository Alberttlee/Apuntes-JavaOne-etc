package bytebank;

public class TestConstructores {

    public static void main(String[] args) {

        Cuenta cuenta1 = new Cuenta(123,456);
        Cuenta cuenta2 = new Cuenta(22,125);
        Cliente cliente1 = new Cliente();
        cuenta1.setTitular(cliente1);

        cuenta1.getTitular().setNombre("Alberto");
        cuenta1.getTitular().setProfesion("Stripper");


        System.out.println(cuenta1);
        System.out.println(cuenta1.getTitular());
        System.out.println(cuenta1.getTitular().getNombre());
        System.out.println(cuenta1.getTitular().getProfesion());

        System.out.println(Cuenta.getTotal());

    }
}

package bytebank;

public class Prueba {

    public static void main(String[] args) {
        Cuenta cuentaDeMarcela = new Cuenta(123,456);
        Cliente cliente = new Cliente();
        //cuentaDeMarcela.setTitular(cliente);
        //cuentaDeMarcela.getTitular().setNombre("Marcela");

        cliente.setNombre("majo");
        System.out.println(cuentaDeMarcela.getTitular().getNombre());

        System.out.println(cuentaDeMarcela.getTitular().getNombre());
        System.out.println(cuentaDeMarcela);
        System.out.println(cuentaDeMarcela.getTitular());
        System.out.println(cuentaDeMarcela.getTitular().getNumeroIdentidad());
        System.out.println(cuentaDeMarcela.getTitular().getProfesion());
        cuentaDeMarcela.getTitular().setProfesion("doctor");
        System.out.println(cuentaDeMarcela.getTitular().getProfesion());

        cuentaDeMarcela.setNumero(69);
        System.out.println(cuentaDeMarcela.getNumero());
    }
}

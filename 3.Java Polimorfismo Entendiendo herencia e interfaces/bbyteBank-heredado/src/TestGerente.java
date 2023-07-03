public class TestGerente {

    public static void main(String[] args) {

        Gerente gerente1 = new Gerente();
        gerente1.setNombre("Marco");
        gerente1.setDocumentoIdentidad("235568413");
        gerente1.setSalario(5000.0);

        System.out.println(gerente1.getNombre());
        System.out.println(gerente1.getDocumentoIdentidad());
        System.out.println(gerente1.getSalario());

        gerente1.setContrasenia(2222);
        boolean autentico = gerente1.autenticar(2222);

        System.out.println(autentico);
        System.out.println(gerente1.getBonificacion());

    }

}
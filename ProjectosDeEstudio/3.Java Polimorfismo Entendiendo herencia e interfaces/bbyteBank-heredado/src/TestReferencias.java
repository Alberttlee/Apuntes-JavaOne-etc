public class TestReferencias {

    public static void main(String[] args) {
        Funcionario funcionario = new Gerente();
        funcionario.setNombre("Austin");
        funcionario.setSalario(5000.0);

        Gerente clau = new Gerente();
        clau.setNombre("Claudia");
        clau.setSalario(2500.0);

        EditorVideo ramses = new EditorVideo();
        ramses.setNombre("Ramses");
        ramses.setSalario(2000.0);

        Designer ale = new Designer();
        ale.setNombre("Alejdandro");
        ale.setSalario(3000.0);

        ControleBonificacion controle = new ControleBonificacion();
        controle.registrar(funcionario);
        controle.registrar(clau);
        controle.registrar(ramses);
        controle.registrar(ale);

        System.out.println(controle.getSuma());


    }
}

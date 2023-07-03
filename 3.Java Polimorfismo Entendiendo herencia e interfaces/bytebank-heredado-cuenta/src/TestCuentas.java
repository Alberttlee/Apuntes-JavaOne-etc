public class TestCuentas {

    public static void main(String[] args) {

        CuentaCorriente cc = new CuentaCorriente(111, 111);
        cc.depositar(100.0);

        CuentaAhorros ca = new CuentaAhorros(222,222);
        ca.depositar(100.0);

        Cuenta geronimo = new CuentaAhorros(123, 152);
        geronimo.depositar(500.0);

        cc.transferir(10.0, cc);
        System.out.println("CC: " + cc.getSaldo());
        System.out.println("CA: " + ca.getSaldo());
        System.out.println("Geronimo: " + geronimo.getSaldo());


    }
}

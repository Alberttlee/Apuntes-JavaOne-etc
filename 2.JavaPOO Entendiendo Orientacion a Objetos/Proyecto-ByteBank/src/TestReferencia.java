public class TestReferencia {

    public static void main(String[] args) {

        Cuenta primeraCuenta = new Cuenta(2);
        primeraCuenta.depositar(200);

        Cuenta segundaCuenta = primeraCuenta;
        segundaCuenta.depositar(100);

        System.out.println("Saldo primera cuenta; " + primeraCuenta.getSaldo());
        System.out.println("Saldo segunda cuenta; " + segundaCuenta.getSaldo());

        //segundaCuenta.getSaldo() += 400;
        System.out.println("Saldo primera cuenta; " + primeraCuenta.getSaldo());

        System.out.println(primeraCuenta);
        System.out.println(segundaCuenta);

        if (primeraCuenta == segundaCuenta) {
            System.out.println("Son el mismo objeto");
        }
    }
}

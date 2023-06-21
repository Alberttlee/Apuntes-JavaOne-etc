public class CrearCuenta {

    public static void main(String[] args) {
        //  Variable         = Valor
        Cuenta primeraCuenta = new Cuenta(2);
        //primeraCuenta.getSaldo() = 400;
        // primeraCuenta.pais = "Peru"; No compila por que no esta ese atributo
        System.out.println(primeraCuenta.getSaldo());

        Cuenta segundaCuenta = new Cuenta(2); //creamos en la memoria otro objeto de tipo cuenta
        //segundaCuenta.getSaldo() = 400;
        System.out.println(segundaCuenta.getSaldo());
        //System.out.println(primeraCuenta.agencia);

        System.out.println(primeraCuenta);
        System.out.println(segundaCuenta);

        if (primeraCuenta.getSaldo() == segundaCuenta.getSaldo()) {
            System.out.println("Son el mismo objeto");
        } else {
            System.out.println("Son diferentes");
        }
    }
}

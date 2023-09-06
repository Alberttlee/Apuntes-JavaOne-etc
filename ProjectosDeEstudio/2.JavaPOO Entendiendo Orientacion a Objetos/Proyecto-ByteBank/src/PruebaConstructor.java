public class PruebaConstructor {

    public static void main(String[] args) {
        Cuenta cuenta1 = new Cuenta(555);  // le indicamos un valor al objeto
        Cuenta cuenta2 = new Cuenta(333);
        Cuenta cuenta3 = new Cuenta(444);


        System.out.println(Cuenta.getTotal());

        var ternario = (5 % 2 == 0) ? "es par" :  "es impar";
        System.out.println(ternario);

    }
}

package br.com.banco.bytebank.test;

public class TestString {

    public static void main(String[] args){

        String nombre = "Angel";
        String otro = new String("Caramelo");

        String nuevo = otro.replace("C", "c");
        System.out.println(nuevo);

        StringBuilder a = new StringBuilder("hola");
        a.append(" caramelo");
        a.append(1);

        System.out.println(a);

        System.out.println("===============");
        System.out.println();

        String primero = "Corazon";
        String segundo = "Pecesito";

        String tercero = primero.replace(primero,segundo);
        System.out.println(tercero);

        System.out.println(tercero.toUpperCase());

        char c = nombre.charAt(3); //char i
        System.out.println(c);

        int pos = nombre.indexOf("gel");
        System.out.println(pos);

        String sub = nombre.substring(1);
        System.out.println(sub);

        for (int i = 0; i < tercero.length(); i++){
            System.out.println(tercero.charAt(i));
        }

        //isEmpty, trim, contains o split.
        boolean one = tercero.isEmpty();
        System.out.println(one);

        //trim
        System.out.println(tercero.trim());

        //containsa
        System.out.println(tercero.contains("Pecesita"));
        System.out.println(tercero.contains("Pecesito"));


        //

    }

}

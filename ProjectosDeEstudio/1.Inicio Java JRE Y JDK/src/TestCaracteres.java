public class TestCaracteres {

    public static void main(String[] args){

        char letra = 'a';
        System.out.println(letra); // el tipo char contiene solo un carácter de la tabla Unicode

        char valor = 69;     //Compila
        System.out.println(valor);

        valor = (char)(valor + 1);     //Compila
        System.out.println(valor);

        String palabra = "Aqui nace una estrella ";
        System.out.println(palabra);

        palabra = palabra + 2020;
        System.out.println(palabra);

    }
}

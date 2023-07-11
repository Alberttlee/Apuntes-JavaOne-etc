public class Flujo {

    public static void main(String[] args) {
        System.out.println("Inicio de main");
        try {
            metodo1();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Fin do main");
    }
    public static void metodo1() throws MiExcepcion{ //el método es estatico porque donde llamamos este metodo es en main que es estatico, por eso necesita ser estatico.
        System.out.println("Inicio de metodo1");
        metodo2();
        System.out.println("Fin de metodo1");
    }
    public static void metodo2() throws MiExcepcion { //decir explicitamente en la firma del método que lance mi excepcion
        System.out.println("Inicio de metodo2");
        throw new MiExcepcion("Mi excepcion fue lanzada");   //lanzamos el objeto que es la exception

    }
    }
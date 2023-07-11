package enums;
public class Principal {

    Dia day;

    public Principal(Dia day) {
        this.day = day;
    }

    public void tellItLikeItIs() {
        switch (day) {
            case LUNES:
                System.out.println("Lunes son malos");
                break;

            case VIERNES:
                System.out.println("Viernes are better.");
                break;

            case SABADO:
            case DOMINGO:
                System.out.println("Fin de samana es lo mejor");
                break;

            default:
                System.out.println("Mirad de semanas son normales");
                break;
        }
    }


    public static void main(String[] args) {

        Principal firstDay = new Principal(Dia.LUNES);
        firstDay.tellItLikeItIs();
        Principal thirdDay = new Principal(Dia.MIERCOLES);
        thirdDay.tellItLikeItIs();
        Principal fifthDay = new Principal(Dia.VIERNES);
        fifthDay.tellItLikeItIs();
        Principal sixthDay = new Principal(Dia.SABADO);
        sixthDay.tellItLikeItIs();
        Principal seventhDay = new Principal(Dia.DOMINGO);
        seventhDay.tellItLikeItIs();






        for (Dia dia: Dia.values()){
            System.out.println("El día de la semana es: " + dia);
        }

        Dia domingo = Dia.DOMINGO;  //instancia del enum
        System.out.println(domingo.name());  //devuleve un string con el nombre específico de esa constante (mejor usar tiString())
        System.out.println(domingo.ordinal()); //devuelve el número en el que se encuentra,su posicion en este array por decirlo asi empieza en 0 en la cadena
        System.out.println(domingo.toString()); //Convierte del tipo enum a string, devuelve el nombre de esta constante


        Colores colores = Colores.ROSA;
        System.out.println(colores.name());
        System.out.println(colores.hashCode());

        for(Colores colores1: Colores.values()){
            System.out.println("El color seleccionado es: " + colores1);
        }





    }

}

public class TestConexion {

    public static void main(String[] args) throws Exception {
        try (Conexion con = new Conexion()){  //recursos
            con.leerDatos();
        } catch (IllegalStateException ex){
            System.out.println("Ejecutando catch");
            ex.printStackTrace();
        }


        /*
        Conexion con = null;
        try {
            con = new Conexion();
            con.leerDatos();;
        } catch (IllegalStateException ex){
            ex.printStackTrace();
        } finally {   //Aseguramos el cierre o que ejecute si hay o no hay error
            System.out.println("Ejecutando finally");
            if(con != null){
                con.cerrar();
            }

        }

         */


    }
}

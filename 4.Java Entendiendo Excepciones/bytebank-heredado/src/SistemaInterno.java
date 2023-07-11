public class SistemaInterno {

    private String clave = "AluraCursosOnLine";

    public boolean autentica(Autenticable usuario){                // autenticable es una interfaz, el metodo llama a toodo aquel
        boolean puedeIniciarSesion = usuario.iniciarSesion(clave); // que tenga autenticable aplicado,
        if(puedeIniciarSesion){                                    //toodo que tenga la eiqueta autenticable podra usar el método autentica
            System.out.println("Login Exitoso");
            return true;
        } else {
            System.out.println("Error en login");
            return false;
        }
    }

}

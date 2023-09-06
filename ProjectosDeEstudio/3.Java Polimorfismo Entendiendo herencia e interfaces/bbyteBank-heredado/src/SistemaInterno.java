public class SistemaInterno {

    private int contrasenia = 2222;

     public void autenticar(Autenticacion usuario){
        boolean autentico = usuario.autenticar(this.contrasenia);

        if (autentico){
            System.out.println("Puede entrar al sistema");
        } else {
            System.out.println("No puede entrar al sistema");
        }
     }
}

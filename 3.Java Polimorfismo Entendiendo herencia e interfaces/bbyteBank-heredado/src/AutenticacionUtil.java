public class AutenticacionUtil {

    private int contrasenia;

    public void setContrasenia(int contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean autenticar(int contrasenia){
        if (this.contrasenia == contrasenia){
            return true;
        } else {
            return false;
        }
    }

}

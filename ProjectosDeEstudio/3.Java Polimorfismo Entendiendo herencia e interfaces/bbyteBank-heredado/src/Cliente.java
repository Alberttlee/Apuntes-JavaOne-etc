public class Cliente implements Autenticacion{

    private AutenticacionUtil autenticador;

    public Cliente(){
        this.autenticador = new AutenticacionUtil();
    }

    @Override
    public void setContrasenia(int contrasenia) {
        this.autenticador.setContrasenia(contrasenia);
    }

    @Override
    public boolean autenticar(int contrasenia) {
        return this.autenticador.autenticar(contrasenia);
    }
}

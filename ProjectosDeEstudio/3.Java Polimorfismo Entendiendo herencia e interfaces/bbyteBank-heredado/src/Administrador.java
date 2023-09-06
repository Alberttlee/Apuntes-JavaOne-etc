public class Administrador extends Funcionario implements Autenticacion{

    private AutenticacionUtil autenticador;

    public Administrador(){
        this.autenticador = new AutenticacionUtil();
    }

    @Override
    public double getBonificacion() {
        return 0;
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

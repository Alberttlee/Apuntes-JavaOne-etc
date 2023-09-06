public class Gerente extends Funcionario implements Autenticacion{

    private AutenticacionUtil autenticador;


    public Gerente(){
        this.autenticador = new AutenticacionUtil();
    }

    @Override
    public void setContrasenia(int contrasenia) {
        this.autenticador.setContrasenia(contrasenia);
    }

    @Override
    public boolean autenticar(int contrasenia){
        return this.autenticador.autenticar(contrasenia);
    }

    @Override
    public double getBonificacion() {
        double defaultBoni = super.getSalario() + 0.1;
        return defaultBoni + super.getSalario();
    }
}
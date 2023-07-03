public class ControleBonificacion {

    private double suma;

    public double getSuma() {
        return this.suma;
    }

    public void registrar(Funcionario funcionario){
        double boni = funcionario.getBonificacion();
        this.suma = this.suma + boni;
    }
}

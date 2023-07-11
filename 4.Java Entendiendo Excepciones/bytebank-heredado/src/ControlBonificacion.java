public class ControlBonificacion {

    private double suma;

    public double registrarSalario(Funcionario funcionario){ //acepta cualquier objeto que sea del tipo Funcionario
        this.suma = funcionario.getBonificacion() + this.suma;
        System.out.println("Calculo actual " + this.suma);
        return this.suma;
    }

}

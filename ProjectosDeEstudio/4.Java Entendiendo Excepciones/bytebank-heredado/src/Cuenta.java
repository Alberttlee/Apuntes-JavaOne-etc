//entidad Cuenta:
public abstract class Cuenta {
    protected double saldo;  // protected es solo accesible desde las clases hijas
    private int agencia;              //campos, variable de instancia
    private int numero;
    private Cliente titular = new Cliente();
    private static int total = 0;   //Variable de clase (static)

    public Cuenta(){

    }

    public Cuenta(int agencia, int numero){
        this.numero = numero;
        if (agencia <= 0){
            System.out.println("No se permite 0");    //Constructor
            this.agencia = 1;
        } else {
            this.agencia = agencia;
        }
        total++;
        System.out.println("Estoy creando una cuenta " + this.numero);
    }

    //No retorna valor
     public abstract void depositar(double valor);

    public void retirar(double valor) throws SaldoInsufucienteException { //avisando que este metodo va a lanzar una exception
        if (this.saldo < valor){
            throw new SaldoInsufucienteException("No tiene saldo \n" +
                    "Saldo: " + this.saldo + ", Valor: " + valor);
        }
        this.saldo -= valor;
    }

    public boolean transferir(double valor, Cuenta destino) {
        if (this.saldo >= valor) {
            try {
                this.retirar(valor);
            } catch (SaldoInsufucienteException e) {
                e.printStackTrace(System.out);
            }
            destino.depositar(valor);
            return true;
        }
            return false;
    }

    //Retorna valor
    public double getSaldo(){
        return this.saldo;
    }

    public void setAgencia(int agencia){         //Si cada cuenta es para cada agencia, no tiene caso  tener el metodo getAgencia
         if (agencia > 0 ) {                     // ya que al instanciar la clase Cuenta, este nos pide un numero de agencia
             this.agencia = agencia;             // por lo tanto si cada cuenta es creada para una agencia setAgencia no es utilizada por regla de negocio
         } else {
             System.out.println("No estan permitidos valores negativos");
         }
    }

    public int getAgencia() {
         return agencia;
    }

    public void setTitular(Cliente titular) {
         this.titular = titular;
    }

    public Cliente getTitular() {
         return titular;
    }

    public static int getTotal() {
        return Cuenta.total;
    }

}

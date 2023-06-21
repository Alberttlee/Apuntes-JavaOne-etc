//entidad Cuenta:
class Cuenta {
    private double saldo;
    private int agencia;              //campos, variable de instancia
    private int numero;
    private Cliente titular = new Cliente();
    private static int total = 0;   //Variable de clase (static)

    public Cuenta(int agencia){
        if (agencia <= 0){
            System.out.println("No se    permite 0");    //Constructor
            this.agencia = 1;
        } else {
            this.agencia = agencia;
        }
        total++;
        System.out.println("Se van creando: " + total + " Cuentas");
    }

    //No retorna valor
     public void depositar(double valor) {
         /*Esta cuenta
         This account                               metodos
         This Object */
         this.saldo += valor;
    }

    //Retorna valor
    public boolean retirar(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        }
            return false;
    }

    public boolean transferir(double valor, Cuenta cuenta) {
        if (this.saldo >= valor) {
            retirar(valor);
            cuenta.depositar(valor);
            return true;
        }
            return false;
    }

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

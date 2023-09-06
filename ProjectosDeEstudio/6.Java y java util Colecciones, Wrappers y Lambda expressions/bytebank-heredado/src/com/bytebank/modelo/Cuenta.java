package com.bytebank.modelo;

/**
 * Cuenta va a crear nuevas instancias de Cuenta corriente
 *
 * @version 1.0
 * @author Albertt
 */
public abstract class Cuenta implements Comparable<Cuenta>{

    // public       // Acesible desde cualquier parte
    // --default    // Accesible desntro del paquete
    // --protected  // default + clases hijas
    // ---private   // solo desde la misma clase

    protected double saldo;  // protected es solo accesible desde las clases hijas
    private int agencia;              //campos, variable de instancia
    private int numero;
    private Cliente titular = new Cliente();
    private static int total = 0;   //Variable de clase (static)

    /**
     * Instancia una nueva Cuenta sin parametros
     */
    public Cuenta(){

    }

    /**
     * Instancia una cuenta usando agencia y numero
     * @param agencia
     * @param numero
     */
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

    /**
     * Este metodo retira dinero de la cuenta y si ocurre un error
     * devuelve una excepcion
     * @param valor
     * @throws SaldoInsufucienteException
     */
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
         if (agencia > 0 ) {                     // ya que al instanciar la clase Model.Cuenta, este nos pide un numero de agencia
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

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        String cuenta = "Numero: " + this.numero + " Agencia: " + this.agencia + " Titular: " + this.titular.getNombre();
        return cuenta;
    }

    @Override
    public boolean equals(Object obj) {
        Cuenta cuenta = (Cuenta) obj;
        return this.agencia == cuenta.getAgencia() && this.numero == cuenta.getNumero();
    }

    @Override
    public int compareTo(Cuenta o) {
        //Orden Natural: Numero de agencia
        return Integer.compare(this.agencia, o.getAgencia());
        /// Orden natural: Saldo
        //return Double.compare(this.getSaldo(), o.getSaldo());
    }

}

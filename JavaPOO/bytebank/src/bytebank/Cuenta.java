package bytebank;

public class Cuenta {

     private double saldo;
     private int agencia;
     private int numero;
     private Cliente titular = new Cliente();
     private static int total = 0;


     public Cuenta(int agencia, int numero){
          this.saldo = 100;  //Cada cuenta comienza con 100
          this.agencia = agencia;
          this.numero = numero;
          System.out.println("Creando cuenta");
          Cuenta.total++;
          System.out.println("Total de cuentas es " + Cuenta.total);
          System.out.println("Estoy creando una cuenta" + this.numero);
     }

     public void deposita(double valor) {
          this.saldo += valor;
     }

     public boolean saca(double valor){
          if (this.saldo >= valor){
               this.saldo -= valor;
               return true;
          }
          return false;
     }

     public boolean transfiere(double valor, Cuenta destino){
          if (this.saldo >= valor){
               saca(valor);
               destino.deposita(valor);
               return true;
          }
          return false;
     }

     public double getSaldo() {
          return saldo;
     }

     public void setAgencia(int agencia) {
          this.agencia = agencia;
     }

     public int getAgencia() {
          return agencia;
     }

     public void setNumero(int numero) {
          this.numero = numero;
     }

     public int getNumero() {
          return numero;
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


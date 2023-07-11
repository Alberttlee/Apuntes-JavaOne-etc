public class CuentaCorriente extends Cuenta implements Tributacion{

    public CuentaCorriente(int agencia, int numero){
        super(agencia, numero);
    }

    @Override
    public void depositar(double valor) {
        super.saldo += valor;
    }

    @Override
    public void retirar(double valor) throws SaldoInsuficienteException {
        double valorARetirar = valor + 0.2;
        super.retirar(valorARetirar);
    }

    @Override
    public double getValorImpuesto() {
        return super.saldo * 0.1;
    }
}

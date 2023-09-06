package br.com.banco.bytebank.test;

import br.com.banco.bytebank.modelo.Cuenta;
import br.com.banco.bytebank.modelo.CuentaCorriente;
import br.com.banco.bytebank.modelo.GuardadorCuentas;

public class Test {

    public static void main(String[] args) {

        GuardadorCuentas guardador = new GuardadorCuentas();

        Cuenta cc = new CuentaCorriente(22,11);
        guardador.adicionar(cc);

        Cuenta cc2 = new CuentaCorriente(22,22);
        guardador.adicionar(cc2);

        int tamano = guardador.getCantidadElementos();
        System.out.println(tamano);

        Cuenta ref = guardador.getRerencia(0);
        System.out.println(ref.getNumero());

    }

}

package br.com.banco.bytebank.test;

import br.com.banco.bytebank.modelo.Cuenta;
import br.com.banco.bytebank.modelo.CuentaCorriente;
import br.com.banco.bytebank.modelo.GuardarReferencias;

public class TestGuardaReferencias {

    public static void main(String[] args) {

        GuardarReferencias gr = new GuardarReferencias();

        Cuenta cc = new CuentaCorriente(12,34);
        gr.adicionar(cc);

        Cuenta cc2 = new CuentaCorriente(22,22);
        gr.adicionar(cc2);

        int numero = gr.getCantidadElelmentos();
        gr.adicionar(numero);

        Cuenta ref = (Cuenta)gr.getReferencia(0);

        System.out.println(ref.getNumero());
        System.out.println(gr.getReferencia(1));
        System.out.println(gr.getReferencia(0));


    }


}

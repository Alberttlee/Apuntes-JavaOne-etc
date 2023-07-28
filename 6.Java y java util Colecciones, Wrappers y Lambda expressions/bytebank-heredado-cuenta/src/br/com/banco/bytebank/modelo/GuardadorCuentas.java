package br.com.banco.bytebank.modelo;


public class GuardadorCuentas {

    private Cuenta[] referencias;
    private int posicionLibre = 0;

    public GuardadorCuentas(){
        this.referencias = new Cuenta[10];
    }


    public void adicionar(Cuenta ref) {
        referencias[this.posicionLibre] = ref;
        posicionLibre++;

    }

    public int getCantidadElementos() {
        return this.posicionLibre;
    }

    public Cuenta getRerencia(int pos) {
        return this.referencias[pos];
    }
}

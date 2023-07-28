package br.com.banco.bytebank.modelo;

public class GuardarReferencias {

    private Object[] referenciasObj;
    private int posicion= 0;


    public GuardarReferencias(){
        referenciasObj = new Object[10];
    }

    public void adicionar(Object ref) {
        referenciasObj[this.posicion] = ref;
        posicion++;

    }

    public int getCantidadElelmentos() {
        return this.posicion;
    }


    public Object getReferencia(int pos) {
        return this.referenciasObj[pos];
    }
}

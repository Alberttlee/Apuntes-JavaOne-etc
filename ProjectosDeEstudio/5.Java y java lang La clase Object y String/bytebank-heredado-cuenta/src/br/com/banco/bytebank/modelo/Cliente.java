package br.com.banco.bytebank.modelo;

/**
 * Clase que representa a un cliente en ByteBank
 *
 * @author Albertt
 * @version 0.1
 */
public class Cliente {
    private String nombre;
    private String documento;
    private String telefono;


    public Cliente(){
                                                    //constructor por defecto, los valores son null o "0"
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

public abstract interface Autenticacion {

    public void setContrasenia(int contrasenia);

    public abstract boolean autenticar(int contrasenia);

}
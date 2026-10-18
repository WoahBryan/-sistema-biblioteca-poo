package edu.utj.dsm.poo.biblioteca.modelo;

/**
 * Clase que representa a un usuario o cliente de la biblioteca.
 * Se define como final para impedir la creación de subclases.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public final class Usuario extends Persona {
    private String numeroCredencial;
    private boolean activo;

    /**
     * Constructor vacío requerido para frameworks e instanciación por defecto.
     */
    public Usuario() {
        super();
        this.numeroCredencial = "";
        this.activo = false;
    }

    /**
     * Constructor con los atributos mínimos indispensables.
     * 
     * @param id Identificador único de la persona.
     * @param nombre Nombre de pila.
     * @param primerApellido Primer apellido.
     * @param numeroCredencial Matrícula o número de identificación en la biblioteca.
     */
    public Usuario(int id, String nombre, String primerApellido, String numeroCredencial) {
        super(id, nombre, primerApellido);
        this.numeroCredencial = numeroCredencial;
        this.activo = true;
    }

    /**
     * Obtiene el número de credencial del usuario.
     * @return número de credencial.
     */
    public String getNumeroCredencial() {
        return numeroCredencial;
    }

    /**
     * Establece el número de credencial.
     * @param numeroCredencial credencial a asignar.
     */
    public void setNumeroCredencial(String numeroCredencial) {
        this.numeroCredencial = numeroCredencial;
    }

    /**
     * Verifica si el usuario está activo.
     * @return true si está activo, false en caso contrario.
     */
    public boolean isActivo() {
        return activo;
    }
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Establece el estado de actividad del usuario.
     * @param activo estado a asignar.
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return super.toString() + " Usuario{" + "numeroCredencial=" + numeroCredencial + ", activo=" + activo + '}';
    }
}
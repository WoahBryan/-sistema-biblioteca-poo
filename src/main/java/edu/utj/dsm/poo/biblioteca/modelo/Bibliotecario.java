package edu.utj.dsm.poo.biblioteca.modelo;

/**
 * Clase que representa a un empleado de la biblioteca.
 * Se define como final para evitar la derivación posterior de clases.
 * 
 * @author CONTRERAS MARTINEZ BRYAN DANIEL
 */
public final class Bibliotecario extends Persona {
    private String turno;
    private String area;

    /**
     * Constructor vacío requerido para frameworks e instanciación por defecto.
     */
    public Bibliotecario() {
        super();
        this.turno = "";
        this.area = "";
    }

    /**
     * Constructor con los atributos mínimos indispensables.
     * 
     * @param id Identificador único de la persona.
     * @param nombre Nombre de pila.
     * @param primerApellido Primer apellido.
     * @param turno Turno laboral asignado.
     * @param area Área de trabajo en la biblioteca.
     */
    public Bibliotecario(int id, String nombre, String primerApellido, String turno, String area) {
        super(id, nombre, primerApellido);
        this.turno = turno;
        this.area = area;
    }

    /**
     * Obtiene el turno del bibliotecario.
     * @return turno asignado.
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Establece el turno del bibliotecario.
     * @param turno turno a asignar.
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Obtiene el área del bibliotecario.
     * @return área asignada.
     */
    public String getArea() {
        return area;
    }

    /**
     * Establece el área del bibliotecario.
     * @param area área a asignar.
     */
    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return super.toString() + " Bibliotecario{" + "turno=" + turno + ", area=" + area + '}';
    }
}
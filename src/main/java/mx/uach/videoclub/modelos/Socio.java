package mx.uach.videoclub.modelos;

import javax.persistence.Entity;
import mx.uach.videoclub.modelos.genericos.Model;

/**
 * Modelo para mappear los socios del video club.
 *
 * @author Erik David Zubia Hernández
 * @version 1.0
 */
@Entity
public class Socio extends Model{
    
    public static final String TABLA = "socios";
    public static final String TABLA_HIBERNATE = "Socio";
    public static final String[] FIELDS = {"id", "nombre", "direccion", "telefono"};
    public static final String Q = String.format("SELECT %s FROM %s", fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);
    public static final String Q_HIBERNATE = String.format("SELECT a FROM %s",  TABLA_HIBERNATE);
    public static final String INSERT_SOCIO = String.format("%s %s (%s) VALUES (%s)", 
            Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE), paramsToStatement(FIELDS, Boolean.TRUE) );
    public static final String UPDATE_SOCIO = String.format("%s %s SET %s WHERE %s = ?", Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE), ID);
    public static final String DELETE_SOCIO = String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);
    
    private String nombre;
    private String direccion;
    private String telefono;

    /**
     * Constructor vacío.
     */
    public Socio() {
    }

    /**
     * Constructor con los parámetros necesarios para crear describir a un
     * {@code Socio}.
     * 
     * @param nombre {@code String} nombre del socio
     * @param direccion {@code String} dirección del socio
     * @param telefono {@code String} teléfono del socio
     */
    public Socio(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    /**
     * Constructor con los parámetros necesarios para crear describir a un
     * {@code Socio}, adicionado de su identificador único dentro de la
     * base de datos.
     * 
     * @param nombre {@code String} nombre del socio
     * @param direccion {@code String} dirección del socio
     * @param telefono {@code String} teléfono del socio
     * @param id {@code Integer} identificador único
     */
    public Socio(String nombre, String direccion, String telefono, Long id) {
        super(id);
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    /**
     * Consigue el nombre del {@code Socio}.
     * 
     * @return {@code String} nombre del Socio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nombre al {@code Socio}.
     * 
     * @param nombre {@code String} nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Consigue el nombre del {@code Socio}.
     * 
     * @return {@code String} dirección del Socio
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Asigna una dirección al {@code Socio}.
     * 
     * @param direccion {@code String} dirección a asignar
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Consigue el teléfono del {@code Socio}.
     * 
     * @return {@code String} teléfono del Socio
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna un teléfono al {@code Socio}.
     * 
     * @param telefono {@code String} teléfono a asignar
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
    
    
}

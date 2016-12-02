package mx.uach.videoclub.modelos;

import javax.persistence.Entity;
import mx.uach.videoclub.modelos.genericos.Model;

/**
 * Modelo para mappear los actores de las películas del video club.
 *
 * @author Erik David Zubia Hernández
 * @version 1.0
 */
@Entity
public class Actor extends Model{
    
    public static final String TABLA = "actores";
    public static final String TABLA_HIBERNATE = "Actor";
    public static final String[] FIELDS = {"id", "nombre", "apellido"};
    public static final String Q = String.format("SELECT %s FROM %s", fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);
    public static final String Q_HIBERNATE = String.format("SELECT a FROM %s",  TABLA_HIBERNATE);
    public static final String INSERT_ACTOR = String.format("%s %s (%s) VALUES (%s)", 
            Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE), paramsToStatement(FIELDS, Boolean.TRUE) );
    public static final String UPDATE_ACTOR = String.format("%s %s SET %s WHERE %s = ?", Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE), ID);
    public static final String DELETE_ACTOR = String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);   

    private String nombre;
    private String apellido;

    /**
     * Constructor vacío.
     */
    public Actor() {
    }

    /**
     * Constructor con los parámetros necesarios para crear describir a un
     * {@code Actor}.
     * 
     * @param nombre {@code String} nombre del actor
     * @param apellido {@code String} apellido del actor 
     */
    public Actor(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    /**
     * Constructor con los parámetros necesarios para crear describir a un
     * {@code Actor}, adicionado de su identificador único dentro de la
     * base de datos.
     * 
     * @param nombre {@code String} nombre del actor
     * @param apellido {@code String} apellido del actor 
     * @param id {@code Integer} identificador único
     */
    public Actor(String nombre, String apellido, Long id) {
        super(id);
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /**
     * Consigue el nombre del {@code Actor}.
     * 
     * @return {@code String} nombre del actor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nombre al {@code Actor}.
     * 
     * @param nombre {@code String} nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Consigue el apellido del {@code Actor}.
     * 
     * @return {@code String} apellido del actor
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Asigna un apellido al {@code Actor}.
     * 
     * @param apellido {@code String} apellido a asignar
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
    
    
    
}

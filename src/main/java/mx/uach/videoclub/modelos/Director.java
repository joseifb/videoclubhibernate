package mx.uach.videoclub.modelos;

import javax.persistence.Entity;
import mx.uach.videoclub.modelos.genericos.Model;

/**
 * Modelo para mappear los directores de las películas del video club.
 *
 * @author Erik David Zubia Hernández
 * @version 1.0
 */
@Entity
public class Director extends Model {

    public static final String TABLA = "directores";
    public static final String TABLA_HIBERNATE = "Director";
    public static final String[] FIELDS = {"id", "nombre"};
    public static final String Q = String.format("SELECT %s FROM %s", fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);
    public static final String Q_HIBERNATE = String.format(HIBERNATE,  TABLA_HIBERNATE);
    public static final String INSERT_DIRECTOR = String.format("%s %s (%s) VALUES (%s)", 
            Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE), paramsToStatement(FIELDS, Boolean.TRUE) );
    public static final String UPDATE_DIRECTOR = String.format("%s %s SET %s WHERE %s = ?", Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE), ID);
    public static final String DELETE_DIRECTOR = String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);

    private String nombre;

    /**
     * Constructor vacío.
     */
    public Director() {
    }

    /**
     * Constructor con los parámetros necesarios para crear describir a un
     * {@code Director}.
     * 
     * @param nombre {@code String} nombre del director
     */
    public Director(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Constructor con los parámetros necesarios para crear describir a un
     * {@code Director}, adicionado de su identificador único dentro de la
     * base de datos.
     * 
     * @param nombre {@code String} nombre del director
     * @param id {@code Integer} identificador único
     */
    public Director(Long id, String nombre) {
        this(nombre);
        this.setId(id);
    }

    /**
     * Consigue el nombre del {@code Director}.
     * 
     * @return {@code String} nombre del director
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna un nombre al {@code Director}.
     * 
     * @param nombre {@code String} nombre a asignar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

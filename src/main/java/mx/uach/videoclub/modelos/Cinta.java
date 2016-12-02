package mx.uach.videoclub.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import mx.uach.videoclub.modelos.genericos.Model;

/**
 * Modelo para mappear las cintas de las películas del video club.
 *
 * @author Erik David Zubia Hernández
 * @version 1.0
 */
@Entity
public class Cinta extends Model {
    
    public static final String TABLA = "cintas";
    public static final String TABLA_HIBERNATE = "Cinta";
    public static final String[] FIELDS = {"id", "numero_cinta", "peliculas_id"};
    public static final String Q = String.format("SELECT %s FROM %s", fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);
    public static final String Q_HIBERNATE = String.format("SELECT a FROM %s",  TABLA_HIBERNATE);
    public static final String INSERT_CINTA = String.format("%s %s (%s) VALUES (%s)", 
            Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE), paramsToStatement(FIELDS, Boolean.TRUE) );
    public static final String UPDATE_CINTA = String.format("%s %s SET %s WHERE %s = ?", Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE), ID);
    public static final String DELETE_CINTA = String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);
    
    @Column(name = "numero_cinta")
    private Integer numeroCinta;
    @ManyToOne
    private Pelicula pelicula;

    /**
     * Constructor vacío.
     */
    public Cinta() {
    }

    /**
     * Constructor con los parámetros necesarios para crear describir a una
     * {@code Cinta}.
     * 
     * @param numeroCinta {@code Integer} número de cinta
     * @param pelicula {@code Pelicula} película de la cinta
     */
    public Cinta(Integer numeroCinta, Pelicula pelicula) {
        this.numeroCinta = numeroCinta;
        this.pelicula = pelicula;
    }

    /**
     * Constructor con los parámetros necesarios para crear describir a una
     * {@code Cinta}, adicionado de su identificador único dentro de la
     * base de datos.
     * 
     * @param numeroCinta {@code Integer} número de cinta
     * @param pelicula {@code Pelicula} película de la cinta
     * @param id {@code Integer} identificador único
     */
    public Cinta(Integer numeroCinta, Pelicula pelicula, Long id) {
        super(id);
        this.numeroCinta = numeroCinta;
        this.pelicula = pelicula;
    }

    /**
     * Consigue el número de cinta.
     * 
     * @return {@code Integer} número de la cinta
     */
    public Integer getNumeroCinta() {
        return numeroCinta;
    }

    /**
     * Asigna un número de cinta.
     * 
     * @param numeroCinta {@code Integer} número de la {@code Cinta}
     */
    public void setNumeroCinta(Integer numeroCinta) {
        this.numeroCinta = numeroCinta;
    }

    /**
     * Consigue la {@code Pelicula} de la {@code Cinta}.
     * 
     * @return {@code Pelicula} película de la cinta
     */
    public Pelicula getPelicula() {
        return pelicula;
    }

    /**
     * Asigna una {@code Pelicula} a la {@code Cinta}.
     * 
     * @param pelicula {@code Pelicula} de la {@code Cinta}
     */
    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    
    
    
}

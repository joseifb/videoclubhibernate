package mx.uach.videoclub.modelos;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import mx.uach.videoclub.modelos.enums.EGenero;
import mx.uach.videoclub.modelos.genericos.Model;

/**
 * Modelo para mappear las películas del video club.
 *
 * @author Erik David Zubia Hernández
 * @version 1.0
 */
@Entity
public class Pelicula extends Model{
    
    public static final String TABLA = "peliculas";
    public static final String TABLA_HIBERNATE = "Pelicula";
    public static final String[] FIELDS = {"id", "titulo", "genero", "duracion", "director_id"};
    public static final String Q = String.format("SELECT %s FROM %s", fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);
    public static final String Q_HIBERNATE = String.format("SELECT a FROM %s",  TABLA_HIBERNATE);
    public static final String INSERT_PELICULA = String.format("%s %s (%s) VALUES (%s)", 
            Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE), paramsToStatement(FIELDS, Boolean.TRUE) );
    public static final String UPDATE_PELICULA = String.format("%s %s SET %s WHERE %s = ?", Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE), ID);
    public static final String DELETE_PELICULA = String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);
    
    private String titulo;
    private EGenero genero;
    private Integer duracion;
    @ManyToOne
    private Director director;

    /**
     * Constructor vacío.
     */
    public Pelicula() {
    }

    /**
     * Constructor con los parámetros necesarios para crear describir a una
     * {@code Pelicula}.
     * 
     * @param titulo {@code String} titulo de la película
     * @param genero {@code EGenero} genero de la película
     * @param duracion {@code Integer} duración en minutos de la película
     * @param director {@code Director} director de la película
     */
    public Pelicula(String titulo, EGenero genero, Integer duracion, Director director) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracion = duracion;
        this.director = director;
    }

    /**
     * Constructor con los parámetros necesarios para crear describir a una
     * {@code Pelicula}, adicionado de su identificador único dentro de la
     * base de datos.
     * 
     * @param titulo {@code String} titulo de la película
     * @param genero {@code EGenero} genero de la película
     * @param duracion {@code Integer} duración en minutos de la película
     * @param director {@code Director} director de la película
     * @param id {@code Integer} identificador único
     */
    public Pelicula(String titulo, EGenero genero, Integer duracion, Director director, Long id) {
        super(id);
        this.titulo = titulo;
        this.genero = genero;
        this.duracion = duracion;
        this.director = director;
    }

    /**
     * Consigue el título de la {@code Pelicula}.
     * 
     * @return {@code String} título de la película 
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Asigna un título a la {@code Pelicula}.
     * 
     * @param titulo {@code String} título de la película
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Consigue el genero de la {@code Pelicula}.
     * 
     * @return {@code EGenero} genero de la película 
     */
    public EGenero getGenero() {
        return genero;
    }

    /**
     * Asigna un {@code EGenero} a la {@code Pelicula}.
     * 
     * @param genero {@code EGenero} genero de la película
     */
    public void setGenero(EGenero genero) {
        this.genero = genero;
    }

    /**
     * Consigue la duración de la {@code Pelicula}.
     * 
     * @return {@code Integer} duración de la película 
     */
    public Integer getDuracion() {
        return duracion;
    }

    /**
     * Asigna una duración a la {@code Pelicula}.
     * 
     * @param duracion {@code Integer} duración de la película
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    /**
     * Consigue el {@code Director} de la {@code Pelicula}.
     * 
     * @return {@code Director} director de la película 
     */
    public Director getDirector() {
        return director;
    }

    /**
     * Asigna un {@code Director} a la {@code Pelicula}.
     * 
     * @param director {@code Director} director de la película
     */
    public void setDirector(Director director) {
        this.director = director;
    }
    
    
    
}

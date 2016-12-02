package mx.uach.videoclub.modelos;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import mx.uach.videoclub.modelos.genericos.Model;

/**
 * Modelo para mappear la lista de espera del video club.
 *
 * @author Erik David Zubia Hernández
 * @version 1.0
 */
@Entity
public class Lista extends Model {
    
    public static final String TABLA = "listas";
    public static final String TABLA_HIBERNATE = "Lista";
    public static final String[] FIELDS = {"id", "peliculas_id", "socios_id", "fecha", "hora", "estatus"};
    public static final String Q = String.format("SELECT %s FROM %s", fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);
    public static final String Q_HIBERNATE = String.format("SELECT a FROM %s",  TABLA_HIBERNATE);
    public static final String INSERT_LISTA = String.format("%s %s (%s) VALUES (%s)", 
            Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE), paramsToStatement(FIELDS, Boolean.TRUE) );
    public static final String UPDATE_LISTA = String.format("%s %s SET %s WHERE %s = ?", Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE), ID);
    public static final String DELETE_LISTA = String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);
    
    private Date fecha;
    private Date hora;
    private Boolean estatus;
    @ManyToOne
    private Socio socio;
    @ManyToOne
    private Pelicula pelicula;

    /**
     * Constructor vacío.
     */
    public Lista() {
    }

    /**
     * Constructor con los parámetros necesarios para crear una petición de espera
     * en la lista.
     * 
     * @param fecha {@code Date} fecha en la que se aparto la película
     * @param hora {@code Date} hora en la que se aparto la película
     * @param estatus {@code Boolean} estatus del pedido
     * @param socio {@code Socio} que realizó el apartado
     * @param pelicula {@code Pelicula} que aparto el {@code Socio}
     */
    public Lista(Date fecha, Date hora, Boolean estatus, Socio socio, Pelicula pelicula) {
        this.fecha = fecha;
        this.hora = hora;
        this.estatus = estatus;
        this.socio = socio;
        this.pelicula = pelicula;
    }

    /**
     * Constructor con los parámetros necesarios para crear una petición de espera
     * en la lista, adicionado con su identificador de la base de datos.
     * 
     * @param fecha {@code Date} fecha en la que se aparto la película
     * @param hora {@code Date} hora en la que se aparto la película
     * @param estatus {@code Boolean} estatus del pedido
     * @param socio {@code Socio} que realizó el apartado
     * @param pelicula {@code Pelicula} que aparto el {@code Socio}
     */
    public Lista(Date fecha, Date hora, Boolean estatus, Socio socio, Pelicula pelicula, Long id) {
        super(id);
        this.fecha = fecha;
        this.hora = hora;
        this.estatus = estatus;
        this.socio = socio;
        this.pelicula = pelicula;
    }

    /**
     * Consigue la fecha en la que se realizó el apartado.
     * 
     * @return {@code Date} fecha del apartado 
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Asigna un fecha de apartado.
     * 
     * @param fecha {@code Date} en la que se realizó el apartado
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Consigue la hora en la que se realizó el apartado.
     * 
     * @return {@code Date} hora del apartado 
     */
    public Date getHora() {
        return hora;
    }

    /**
     * Asigna un hora de apartado.
     * 
     * @param hora {@code Date} en la que se realizó el apartado
     */
    public void setHora(Date hora) {
        this.hora = hora;
    }

    /**
     * Consigue el estatus del apartado.
     * 
     * @return {@code Boolean} estatus del apartado
     */
    public Boolean getEstatus() {
        return estatus;
    }

    /**
     * Asigna un estatos al apartado.
     * 
     * @param estatus {@code Boolean} en el que se encuentra apartado
     */
    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    /**
     * Consigue el {@code Socio} que realizó el apartado.
     * 
     * @return {@code Socio} que aparto
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * Asigna un {@code Socio} del apartado
     * 
     * @param socio {@code Socio} que realizó el apartado
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    /**
     * Consigue el {@code Pelicula} que se apartó.
     * 
     * @return {@code Pelicula} que se aparto
     */
    public Pelicula getPelicula() {
        return pelicula;
    }

    /**
     * Asigna un {@code Pelicula} del apartado
     * 
     * @param pelicula {@code Pelicula} que se aparto
     */
    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    
    
    
}

package mx.uach.videoclub.modelos;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import mx.uach.videoclub.modelos.genericos.Model;
import mx.uach.videoclub.modelos.Socio;

/**
 * Modelo para mappear las fichas del video club.
 *
 * @author Erik David Zubia Hernández
 * @version 1.0
 */
@Entity
public class Ficha extends Model {
    
    public static final String TABLA = "fichas";
    public static final String TABLA_HIBERNATE = "Ficha";
    public static final String[] FIELDS = {"id", "fecha_prestamo", "socios_id"};
    public static final String Q = String.format("SELECT %s FROM %s", fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);
    public static final String Q_HIBERNATE = String.format("SELECT a FROM %s",  TABLA_HIBERNATE);
    public static final String INSERT_FICHA = String.format("%s %s (%s) VALUES (%s)", 
            Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE), paramsToStatement(FIELDS, Boolean.TRUE) );
    public static final String UPDATE_FICHA = String.format("%s %s SET %s WHERE %s = ?", Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE), ID);
    public static final String DELETE_FICHA = String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);

    @Column(name = "fecha_prestamo")
    private Date fechaPrestamo;
    
    @ManyToOne(targetEntity = Socio.class)
    private Socio socio;

    /**
     * Constructor vacío.
     */
    public Ficha() {
    }

    /**
     * Constructor con los parámetros necesarios para crear describir a una
     * {@code Ficha}.
     * 
     * @param fechaPrestamo {@code Date} fecha en la que se realizó el prestamo
     * @param socio {@code Socio} al que se le realizó el prestamo
     */
    public Ficha(Date fechaPrestamo, Socio socio) {
        this.fechaPrestamo = fechaPrestamo;
        this.socio = socio;
    }

    /**
     * Constructor con los parámetros necesarios para crear y describir a una
     * {@code Ficha}, adicionado de su identificador único dentro de la
     * base de datos.
     * 
     * @param fechaPrestamo {@code Date} fecha en la que se realizó el prestamo
     * @param socio {@code Socio} al que se le realizó el prestamo
     * @param id {@code Integer} identificador único
     */
    public Ficha(Date fechaPrestamo, Socio socio, Long id) {
        super(id);
        this.fechaPrestamo = fechaPrestamo;
        this.socio = socio;
    }

    /**
     * Consigue la fecha cuando se realizó el prestamo.
     * 
     * @return {@code Date} fecha del prestamo
     */
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Asigna una fecha de prestamo.
     * 
     * @param fechaPrestamo {@code Date} fecha del prestamo
     */
    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * Consigue el {@code Socio} al que se le realizó el prestamo.
     * 
     * @return {@code Socio} de la ficha
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * Asigna un {@code Socio} a la ficha.
     * 
     * @param socio {@code Socio} a asignar
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    
    
    
}

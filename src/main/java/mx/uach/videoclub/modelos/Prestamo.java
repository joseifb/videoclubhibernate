package mx.uach.videoclub.modelos;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import mx.uach.videoclub.modelos.enums.EEstatusPrestamo;
import mx.uach.videoclub.modelos.genericos.Model;

/**
 * Modelo para mappear los prestamos de las películas del video club.
 *
 * @author Erik David Zubia Hernández
 * @version 1.0
 */
@Entity
public class Prestamo extends Model {
    
    public static final String TABLA = "prestamos";
    public static final String TABLA_HIBERNATE = "Prestamo";
    public static final String[] FIELDS = {"id", "cintas_id", "fichas_id", "fecha_entrega", "estatus"};
    public static final String Q = String.format("SELECT %s FROM %s", fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);
    public static final String Q_HIBERNATE = String.format("SELECT a FROM %s",  TABLA_HIBERNATE);
    public static final String INSERT_PRESTAMO = String.format("%s %s (%s) VALUES (%s)", 
            Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE), paramsToStatement(FIELDS, Boolean.TRUE) );
    public static final String UPDATE_PRESTAMO = String.format("%s %s SET %s WHERE %s = ?", Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE), ID);
    public static final String DELETE_PRESTAMO = String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);
    
    private Date fechaEntrega;
    private EEstatusPrestamo estatus;
    @ManyToOne
    private Ficha ficha;
    @ManyToOne
    private Cinta cinta;

    /**
     * Constructor vacío.
     */
    public Prestamo() {
    }

    /**
     * Constructor con los parámetros necesarios para crear un
     * {@code Prestamo}.
     * 
     * @param fechaEntrega  {@code Date} fecha para entregar
     * @param estatus {@code EEstatusPrestamo} estatus en el que se encuentra el prestamo
     * @param ficha {@code Ficha} que verifica el prestamo
     * @param cinta {@code Cinta} que se prestó 
     * 
     */
    public Prestamo(Date fechaEntrega, EEstatusPrestamo estatus, Ficha ficha, Cinta cinta) {
        this.fechaEntrega = fechaEntrega;
        this.estatus = estatus;
        this.ficha = ficha;
        this.cinta = cinta;
    }

    /**
     * Constructor con los parámetros necesarios para crear un
     * {@code Prestamo}, con el identificador único de la base de datos.
     * 
     * @param fechaEntrega  {@code Date} fecha para entregar
     * @param estatus {@code EEstatusPrestamo} estatus en el que se encuentra el prestamo
     * @param ficha {@code Ficha} que verifica el prestamo
     * @param cinta {@code Cinta} que se prestó 
     * @param id {@code Integer} identificador de la base de datos
     * 
     */
    public Prestamo(Date fechaEntrega, EEstatusPrestamo estatus, Ficha ficha, Cinta cinta, Long id) {
        super(id);
        this.fechaEntrega = fechaEntrega;
        this.estatus = estatus;
        this.ficha = ficha;
        this.cinta = cinta;
    }

    /**
     * Consigue la fecha de entrega del {@code Prestamo}.
     * 
     * @return {@code Date} con la fecha para entregar la película
     */
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * Asigna una fecha de entrega al {@code Prestamo}.
     * 
     * @param fechaEntrega {@code Date} en la que se tiene que retornar la película
     */
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * Consigue el {@code EEstatusPrestamo} del {@code Prestamo}.
     * 
     * @return {@code EEstatusPrestamo} actual del prestamo
     */
    public EEstatusPrestamo getEstatus() {
        return estatus;
    }
    
    /**
     * Asigna un {@code EEstatusPrestamo} al {@code Prestamo}.
     * 
     * @param estatus {@code EEstatusPrestamo} en el que se encuentra el prestamo
     */
    public void setEstatus(EEstatusPrestamo estatus) {
        this.estatus = estatus;
    }

    /**
     * Consigue la {@code Ficha} de identificación del {@code Prestamo}.
     * 
     * @return {@code Ficha} actual del prestamo
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     * Asigna una {@code Ficha} al {@code Prestamo}.
     * 
     * @param ficha {@code Ficha} de identificación del prestamo
     */
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    /**
     * Consigue la {@code Cinta} que se presto.
     * 
     * @return {@code Cinta} del prestamo
     */
    public Cinta getCinta() {
        return cinta;
    }

    /**
     * Asigna una {@code Cinta} al {@code Prestamo}.
     * 
     * @param cinta {@code Cinta} que se prestara
     */
    public void setCinta(Cinta cinta) {
        this.cinta = cinta;
    }
    
    
    
}

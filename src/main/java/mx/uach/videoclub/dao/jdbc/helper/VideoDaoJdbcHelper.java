package mx.uach.videoclub.dao.jdbc.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.uach.videoclub.dao.VideoDao;
import mx.uach.videoclub.dao.jdbc.VideoDaoJDBC;
import mx.uach.videoclub.modelos.Actor;
import mx.uach.videoclub.modelos.Cinta;
import mx.uach.videoclub.modelos.Director;
import mx.uach.videoclub.modelos.Ficha;
import mx.uach.videoclub.modelos.Lista;
import mx.uach.videoclub.modelos.Pelicula;
import mx.uach.videoclub.modelos.Prestamo;
import mx.uach.videoclub.modelos.Socio;
import mx.uach.videoclub.modelos.enums.EEstatusPrestamo;
import mx.uach.videoclub.modelos.enums.EGenero;

/**
 *
 * Helper que genera objetos del Dao.
 * 
 * @author Erik David Zubia Hernádez.
 */
public class VideoDaoJdbcHelper {
    
    /**
     * Realiza un mapeo de un {@code ResultSet} a un objeto {@code Director}.
     * 
     * @param rs {@code ResultSet} a convertir
     * @return {@code Director} con los parámetros conseguidos por la búsqueda
     * @throws SQLException en caso de que no consida el tipo del campo cuando 
     * lo pedimos
     */
    public final static Director makeDirector(ResultSet rs) throws SQLException{
        Director obj = new Director(rs.getLong(Director.FIELDS[0]), rs.getString(Director.FIELDS[1]));
        return obj;
    }
    
    /**
     * Realiza un mapeo de un {@code ResultSet} a un objeto {@code Actor}.
     * 
     * @param rs {@code ResultSet} a convertir
     * @return {@code Actor} con los parámetros conseguidos por la búsqueda
     * @throws SQLException en caso de que no consida el tipo del campo cuando 
     * lo pedimos
     */
    public final static Actor makeActor(ResultSet rs) throws SQLException{
        Actor obj = new Actor(rs.getString(Actor.FIELDS[1]), rs.getString(Actor.FIELDS[2]), rs.getLong(Actor.FIELDS[0]));
        return obj;
    }
    
    /**
     * Realiza un mapeo de un {@code ResultSet} a un objeto {@code Cinta}.
     * 
     * @param rs {@code ResultSet} a convertir
     * @return {@code Cinta} con los parámetros conseguidos por la búsqueda
     * @throws SQLException en caso de que no consida el tipo del campo cuando 
     * lo pedimos
     */
    public final static Cinta makeCinta(ResultSet rs) throws SQLException{
        Pelicula pelicula;
        VideoDao dao = new VideoDaoJDBC();
        pelicula = (Pelicula) dao.getPeliculaById(rs.getLong(Cinta.FIELDS[2]));
        Cinta obj = new Cinta(rs.getInt(Cinta.FIELDS[1]), pelicula, rs.getLong(Cinta.FIELDS[0]));
        return obj;
    }
    
    /**
     * Realiza un mapeo de un {@code ResultSet} a un objeto {@code Ficha}.
     * 
     * @param rs {@code ResultSet} a convertir
     * @return {@code Ficha} con los parámetros conseguidos por la búsqueda
     * @throws SQLException en caso de que no consida el tipo del campo cuando 
     * lo pedimos
     */
    public final static Ficha makeFicha(ResultSet rs) throws SQLException{
        Socio socio;
        VideoDao dao = new VideoDaoJDBC();
        socio = (Socio) dao.getSocioById(rs.getLong(Ficha.FIELDS[2]));
        Ficha obj = new Ficha(rs.getDate(Ficha.FIELDS[1]), socio, rs.getLong(Ficha.FIELDS[0]));
        return obj;
    }
    
    /**
     * Realiza un mapeo de un {@code ResultSet} a un objeto {@code Lista}.
     * 
     * @param rs {@code ResultSet} a convertir
     * @return {@code Lista} con los parámetros conseguidos por la búsqueda
     * @throws SQLException en caso de que no consida el tipo del campo cuando 
     * lo pedimos
     */
    public final static Lista makeLista(ResultSet rs) throws SQLException{
        VideoDao dao = new VideoDaoJDBC();
        Socio socio = dao.getSocioById(rs.getLong(Lista.FIELDS[2]));
        Pelicula pelicula = dao.getPeliculaById(rs.getLong(Lista.FIELDS[1]));
        Lista obj = new Lista(rs.getDate(Lista.FIELDS[3]), rs.getDate(Lista.FIELDS[4]), rs.getBoolean(Lista.FIELDS[5]), socio, pelicula, rs.getLong(Lista.FIELDS[0]));
        return obj;
    }
    
    /**
     * Realiza un mapeo de un {@code ResultSet} a un objeto {@code Pelicula}.
     * 
     * @param rs {@code ResultSet} a convertir
     * @return {@code Pelicula} con los parámetros conseguidos por la búsqueda
     * @throws SQLException en caso de que no consida el tipo del campo cuando 
     * lo pedimos
     */
    public final static Pelicula makePelicula(ResultSet rs) throws SQLException{
        VideoDao dao = new VideoDaoJDBC();
        Director director = dao.getDirectorById(rs.getLong(Pelicula.FIELDS[4]));
        Pelicula obj = new Pelicula(rs.getString(Pelicula.FIELDS[1]), EGenero.valueOf(rs.getString(Pelicula.FIELDS[2])), rs.getInt(Pelicula.FIELDS[3]), director, rs.getLong(Pelicula.FIELDS[0]));
        return obj;
    }
    
    /**
     * Realiza un mapeo de un {@code ResultSet} a un objeto {@code Prestamo}.
     * 
     * @param rs {@code ResultSet} a convertir
     * @return {@code Prestamo} con los parámetros conseguidos por la búsqueda
     * @throws SQLException en caso de que no consida el tipo del campo cuando 
     * lo pedimos
     */
    public final static Prestamo makePrestamo(ResultSet rs) throws SQLException{
        VideoDao dao = new VideoDaoJDBC();
        Ficha ficha = dao.getFichaById(rs.getLong(Prestamo.FIELDS[2]));
        Cinta cinta = dao.getCintaById(rs.getLong(Prestamo.FIELDS[1]));
        Prestamo obj = new Prestamo(rs.getDate(Prestamo.FIELDS[3]), EEstatusPrestamo.valueOf(rs.getString(Prestamo.FIELDS[4])), ficha, cinta, rs.getLong(Prestamo.FIELDS[0]));
        return obj;
    }
    
    /**
     * Realiza un mapeo de un {@code ResultSet} a un objeto {@code Socio}.
     * 
     * @param rs {@code ResultSet} a convertir
     * @return {@code Socio} con los parámetros conseguidos por la búsqueda
     * @throws SQLException en caso de que no consida el tipo del campo cuando 
     * lo pedimos
     */
    public final static Socio makeSocio(ResultSet rs) throws SQLException{
        Socio obj = new Socio(rs.getString(Socio.FIELDS[1]), rs.getString(Socio.FIELDS[2]), rs.getString(Socio.FIELDS[3]), rs.getLong(Socio.FIELDS[0]));
        return obj;
    }
    
}

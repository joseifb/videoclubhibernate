package mx.uach.videoclub.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.uach.videoclub.conexiones.Conexion;
import mx.uach.videoclub.dao.CRUD;
import mx.uach.videoclub.dao.VideoDao;
import mx.uach.videoclub.dao.jdbc.helper.VideoDaoJdbcHelper;
import mx.uach.videoclub.modelos.Actor;
import mx.uach.videoclub.modelos.Cinta;
import mx.uach.videoclub.modelos.Director;
import mx.uach.videoclub.modelos.Ficha;
import mx.uach.videoclub.modelos.Lista;
import mx.uach.videoclub.modelos.Pelicula;
import mx.uach.videoclub.modelos.Prestamo;
import mx.uach.videoclub.modelos.Socio;


/**
 * Encargado de relizar la logica para la busqueda, actualizacion, creacion y 
 * eliminacion de los datos.
 * 
 * @author Erik David Zubia Hernandez
 * @version 1.0
 */
public class VideoDaoJDBC implements VideoDao{

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar un {@code Director}.
     * 
     * @param id {@code Long} identificador único del {@code Director}
     * @return {@code Director} mapeado con el resultado del búsqueda
     */
    @Override
    public Director getDirectorById(Long id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Director.Q, Director.Q_WHERE_ID, id));
            Director obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeDirector(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Director}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Director} que coincidieron con el criterio
     */
    @Override
    public List<Director> getDirectoresByCriteria(String criterio) {
        List<Director> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Director.Q, criterio.isEmpty() ? "" : Director.Q_WHERE, criterio));
            Director obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeDirector(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Director},
     * la actualización de un {@code Director} encontrado o su eliminación.
     * 
     * @param director {@code Director} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void direactorProcces(Director director, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Director.INSERT_DIRECTOR);
                    ps.setString(1, director.getNombre());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Director.UPDATE_DIRECTOR);
                    ps.setString(1, director.getNombre());
                    ps.setLong(2, director.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Director.DELETE_DIRECTOR);
                    ps.setLong(1, director.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar un {@code Actor}.
     * 
     * @param id {@code Long} identificador único del {@code Actor}
     * @return {@code Actor} mapeado con el resultado del búsqueda
     */
    @Override
    public Actor getActorById(Long id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Actor.Q, Actor.Q_WHERE_ID, id));
            Actor obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeActor(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Actor}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Actor} que coincidieron con el criterio
     */
    @Override
    public List<Actor> getActoresByCriteria(String criterio) {
        List<Actor> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Actor.Q, criterio.isEmpty() ? "" : Actor.Q_WHERE, criterio));
            Actor obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeActor(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Actor},
     * la actualización de un {@code Actor} encontrado o su eliminación.
     * 
     * @param actor {@code Actor} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void actorProcces(Actor actor, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Actor.INSERT_ACTOR);
                    ps.setString(1, actor.getNombre());
                    ps.setString(2, actor.getNombre());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Actor.UPDATE_ACTOR);
                    ps.setString(1, actor.getNombre());
                    ps.setString(2, actor.getNombre());
                    ps.setLong(3, actor.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Actor.DELETE_ACTOR);
                    ps.setLong(1, actor.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar una {@code Cinta}.
     * 
     * @param id {@code Long} identificador único de la {@code Cinta}
     * @return {@code Cinta} mapeada con el resultado del búsqueda
     */
    @Override
    public Cinta getCintaById(Long id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Cinta.Q, Cinta.Q_WHERE_ID, id));
            Cinta obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeCinta(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Cinta}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Actor} que coincidieron con el criterio
     */
    @Override
    public List<Cinta> getCintasByCriteria(String criterio) {
        List<Cinta> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Cinta.Q, criterio.isEmpty() ? "" : Cinta.Q_WHERE, criterio));
            Cinta obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeCinta(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Cinta},
     * la actualización de un {@code Cinta} encontrado o su eliminación.
     * 
     * @param cinta {@code Cinta} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void cintaProcces(Cinta cinta, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Cinta.INSERT_CINTA);
                    ps.setLong(1, cinta.getNumeroCinta());
                    ps.setLong(2, cinta.getPelicula().getId());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Cinta.UPDATE_CINTA);
                    ps.setLong(1, cinta.getNumeroCinta());
                    ps.setLong(2, cinta.getPelicula().getId());
                    ps.setLong(3, cinta.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Cinta.DELETE_CINTA);
                    ps.setLong(1, cinta.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar una {@code Ficha}.
     * 
     * @param id {@code Long} identificador único de la {@code Ficha}
     * @return {@code Ficha} mapeada con el resultado del búsqueda
     */
    @Override
    public Ficha getFichaById(Long id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Ficha.Q, Ficha.Q_WHERE_ID, id));
            Ficha obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeFicha(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Ficha}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Ficha} que coincidieron con el criterio
     */
    @Override
    public List<Ficha> getFichasByCriteria(String criterio) {
        List<Ficha> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Ficha.Q, criterio.isEmpty() ? "" : Ficha.Q_WHERE, criterio));
            Ficha obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeFicha(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Ficha},
     * la actualización de un {@code Ficha} encontrado o su eliminación.
     * 
     * @param ficha {@code Ficha} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void fichaProcces(Ficha ficha, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Ficha.INSERT_FICHA);
                    ps.setDate(1, new java.sql.Date(ficha.getFechaPrestamo().getTime()));
                    ps.setLong(2, ficha.getSocio().getId());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Ficha.UPDATE_FICHA);
                    ps.setDate(1, new java.sql.Date(ficha.getFechaPrestamo().getTime()));
                    ps.setLong(2, ficha.getSocio().getId());
                    ps.setLong(3, ficha.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Ficha.DELETE_FICHA);
                    ps.setLong(1, ficha.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar una {@code Lista}.
     * 
     * @param id {@code Long} identificador único de la {@code Lista}
     * @return {@code Lista} mapeada con el resultado del búsqueda
     */
    @Override
    public Lista getListaById(Long id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Lista.Q, Lista.Q_WHERE_ID, id));
            Lista obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeLista(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Lista}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Lista} que coincidieron con el criterio
     */
    @Override
    public List<Lista> getListasByCriteria(String criterio) {
        List<Lista> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Lista.Q, criterio.isEmpty() ? "" : Lista.Q_WHERE, criterio));
            Lista obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeLista(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Lista},
     * la actualización de un {@code Lista} encontrado o su eliminación.
     * 
     * @param lista {@code Lista} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void listaProcces(Lista lista, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Lista.INSERT_LISTA);
                    ps.setLong(1, lista.getPelicula().getId());
                    ps.setLong(2, lista.getSocio().getId());
                    ps.setDate(3, new java.sql.Date(lista.getFecha().getTime()));
                    ps.setDate(4, new java.sql.Date(lista.getHora().getTime()));
                    ps.setBoolean(5, lista.getEstatus());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Lista.UPDATE_LISTA);
                    ps.setLong(1, lista.getPelicula().getId());
                    ps.setLong(2, lista.getSocio().getId());
                    ps.setDate(3, new java.sql.Date(lista.getFecha().getTime()));
                    ps.setDate(4, new java.sql.Date(lista.getHora().getTime()));
                    ps.setBoolean(5, lista.getEstatus());
                    ps.setLong(6, lista.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Lista.DELETE_LISTA);
                    ps.setLong(1, lista.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar una {@code Pelicula}.
     * 
     * @param id {@code Long} identificador único de la {@code Pelicula}
     * @return {@code Pelicula} mapeada con el resultado del búsqueda
     */
    @Override
    public Pelicula getPeliculaById(Long id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Pelicula.Q, Pelicula.Q_WHERE_ID, id));
            Pelicula obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makePelicula(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Pelicula}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Pelicula} que coincidieron con el criterio
     */
    @Override
    public List<Pelicula> getPeliculaByCriteria(String criterio) {
        List<Pelicula> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Pelicula.Q, criterio.isEmpty() ? "" : Pelicula.Q_WHERE, criterio));
            Pelicula obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makePelicula(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Pelicula},
     * la actualización de un {@code Pelicula} encontrado o su eliminación.
     * 
     * @param pelicula {@code Pelicula} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void peliculaProcces(Pelicula pelicula, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Pelicula.INSERT_PELICULA);
                    ps.setString(1, pelicula.getTitulo());
                    ps.setString(2, pelicula.getGenero().toString());
                    ps.setLong(3, pelicula.getDuracion());
                    ps.setLong(4, pelicula.getDirector().getId());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Pelicula.UPDATE_PELICULA);
                    ps.setString(1, pelicula.getTitulo());
                    ps.setString(2, pelicula.getGenero().toString());
                    ps.setLong(3, pelicula.getDuracion());
                    ps.setLong(4, pelicula.getDirector().getId());
                    ps.setLong(5, pelicula.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Pelicula.DELETE_PELICULA);
                    ps.setLong(1, pelicula.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar un {@code Prestamo}.
     * 
     * @param id {@code Long} identificador único del {@code Prestamo}
     * @return {@code Prestamo} mapeado con el resultado del búsqueda
     */
    @Override
    public Prestamo getPrestamoById(Long id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Prestamo.Q, Prestamo.Q_WHERE_ID, id));
            Prestamo obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makePrestamo(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Prestamo}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Prestamo} que coincidieron con el criterio
     */
    @Override
    public List<Prestamo> getPrestamosByCriteria(String criterio) {
        List<Prestamo> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Prestamo.Q, criterio.isEmpty() ? "" : Prestamo.Q_WHERE, criterio));
            Prestamo obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makePrestamo(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Prestamo},
     * la actualización de un {@code Prestamo} encontrado o su eliminación.
     * 
     * @param prestamo {@code Prestamo} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void prestamoProcces(Prestamo prestamo, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Prestamo.INSERT_PRESTAMO);
                    ps.setLong(1, prestamo.getCinta().getId());
                    ps.setLong(2, prestamo.getFicha().getId());
                    ps.setDate(3, new java.sql.Date(prestamo.getFechaEntrega().getTime()));
                    ps.setString(4, prestamo.getEstatus().toString());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Prestamo.UPDATE_PRESTAMO);
                    ps.setLong(1, prestamo.getCinta().getId());
                    ps.setLong(2, prestamo.getFicha().getId());
                    ps.setDate(3, new java.sql.Date(prestamo.getFechaEntrega().getTime()));
                    ps.setString(4, prestamo.getEstatus().toString());
                    ps.setLong(5, prestamo.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Prestamo.DELETE_PRESTAMO);
                    ps.setLong(1, prestamo.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar un {@code Socio}.
     * 
     * @param id {@code Long} identificador único del {@code Socio}
     * @return {@code Socio} mapeado con el resultado del búsqueda
     */
    @Override
    public Socio getSocioById(Long id) {
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Socio.Q, Socio.Q_WHERE_ID, id));
            Socio obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeSocio(rs);
            }
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Socio}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Socio} que coincidieron con el criterio
     */
    @Override
    public List<Socio> getSociosByCriteria(String criterio) {
        List<Socio> objects = new ArrayList<>();
        try {
            ResultSet rs;
            Statement st = Conexion.getInstance().getCon().createStatement();
            rs = st.executeQuery(String.format("%s %s %s", Socio.Q, criterio.isEmpty() ? "" : Socio.Q_WHERE, criterio));
            Socio obj = null;
            while (rs.next()) {                
                obj = VideoDaoJdbcHelper.makeSocio(rs);
                objects.add(obj);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VideoDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    @Override
    public void socioProcces(Socio socio, CRUD crud) {
        try {
            PreparedStatement ps = null;
            switch (crud) {
                case CREATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Socio.INSERT_SOCIO);
                    ps.setString(1, socio.getNombre());
                    ps.setString(2, socio.getDireccion());
                    ps.setString(3, socio.getTelefono());
                    break;
                case UPDATE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Socio.UPDATE_SOCIO);
                    ps.setString(1, socio.getNombre());
                    ps.setString(2, socio.getDireccion());
                    ps.setString(3, socio.getTelefono());
                    ps.setLong(4, socio.getId());
                    break;
                case DELETE:
                    ps = Conexion.getInstance().getCon().prepareStatement(Socio.DELETE_SOCIO);
                    ps.setLong(1, socio.getId());
                    break;
                default:
                    break;
            }
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }
    
}

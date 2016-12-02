package mx.uach.videoclub.hibernate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mx.uach.videoclub.conexiones.VideoEntityManager;
import mx.uach.videoclub.dao.CRUD;
import mx.uach.videoclub.modelos.Prestamo;
import mx.uach.videoclub.modelos.genericos.Model;


/**
 * Encargado de relizar la logica para la busqueda, actualizacion, creacion y 
 * eliminacion de los datos por medio del .
 * 
 * @author Erik David Zubia Hernandez
 * @version 1.0
 */
public class VideoHibernate<T extends Model> {

    private Class<T> type;

    public VideoHibernate(Class<T> type) {
        this.type = type;
    }
    
    
    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar un {@code Entity}.
     * 
     * @param id {@code Long} identificador único del {@code Director}
     * @return {@code Director} mapeado con el resultado del búsqueda
     */
    public T getEntityById(Long id) {
        T d = (T) VideoEntityManager.getINSTANCE().getEm()
                .createQuery(String.format("%s %s %s", T.HIBERNATE.replace("%s", type.getSimpleName()), T.Q_WHERE_ID, T.ID_HIBERNATE))
                .setParameter("id", id).getSingleResult();
        return d != null ? d : null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Entity}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Director} que coincidieron con el criterio
     */
    public List<T> getEntitysByCriteria(String criterio) {
        Query q;
        q = makeQueryByTuples(makeCriteriosByString(criterio));
        List<T> directores = q.getResultList();
        return directores;
    }

    /**
     * Realiza según la opción mandada, la creación de una nueva {@code Entity},
     * la actualización de una {@code Entity} encontrado o su eliminación.
     * 
     * @param entity {@code Entity} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    public void entityProcces(T entity, CRUD crud) {
        EntityManager em = VideoEntityManager.getINSTANCE().getEm();
        em.getTransaction().begin();
        switch (crud) {
            case CREATE:
            case UPDATE:
                em.persist(entity);
                break;
            case DELETE:
                em.remove(entity);
                break;
            default:
                break;
        }
        em.getTransaction().commit();
    }

    /**
     * Creador de {@code HashMap} mediante un string de criterios en conjuntos de
     * llave valor.
     * 
     * @param criterio {@code String} con todos los valores a buscar en la query
     * @return {@code HashMap} pares llave valor
     */
    private HashMap<String, String> makeCriteriosByString(String criterio) {
        HashMap<String, String> tuples = new HashMap<>();
        if(criterio=="") return tuples;
        List<String> criterios = Arrays.asList(criterio.split(", "));
        criterios.stream().forEach(parameter ->{
            String[] s = {};
            if(parameter.contains("like")){
                s = parameter.trim().split("like");
                tuples.put(parameter.trim().replace(s[1], String.format(" :%s", s[0])), s[1]);
            } else {
                s = parameter.trim().split("=");
                tuples.put(parameter.trim().replace(s[1], String.format(":%s", s[0])), s[1]);
            }
        });
        return tuples;
    }

    /**
     * Generador de {@code Query} por los pares llave-valor mandandos.
     * 
     * @param values {@code HashMap} valores de la query
     * @return {@code Query} ya conformada
     */
    private Query makeQueryByTuples(HashMap<String, String> values) {
        StringBuilder builder = new StringBuilder();
        values.keySet().forEach((string) -> {
            builder.append(",");
            builder.append(string);
        });
        String s = String.format("%s %s ", T.HIBERNATE.replace("%s", type.getSimpleName()), values.isEmpty() ? "" : String.format("%s %s", Model.Q_WHERE, builder.toString().substring(1)));
        Query q = VideoEntityManager.getINSTANCE().getEm()
                .createQuery(s);
        values.keySet().forEach((string) -> {
            q.setParameter(string.split(":")[1].trim(), values.get(string).trim());
        });
        return q;
    }

    public Prestamo getPrestamoById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

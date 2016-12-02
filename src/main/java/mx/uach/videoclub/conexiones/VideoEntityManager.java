package mx.uach.videoclub.conexiones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Manejador de EntityManager para la aplicación.
 * 
 * @author Jose Ignacio Flores
 * @version 1.0
 */
public class VideoEntityManager {
     
    private static VideoEntityManager INSTANCE;
    private EntityManager em;
    
    /**
     * Constructor vacío del VideoEntityManager.
     */
    public VideoEntityManager() {
        initEnityManager();
    }
    
    /**
     * Crea el {@code EntityManager} para realizar las operaciónes de base de datos.
     */
    private void initEnityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoPU");
        em = emf.createEntityManager();
    }

    /**
     * Consigue la instancia que tiene el {@code EntityManager}.
     * 
     * @return {@code VideoEntityManager} instancia de conexión a la base de datos
     */
    public static VideoEntityManager getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new VideoEntityManager();
        }
        return INSTANCE;
    }

    /**
     * Retorna el {@code EntityManager} con la conexión establecida.
     * 
     * @return {@code EntityManager} manejador de entidades
     */
    public EntityManager getEm() {
        return em;
    }
    
}

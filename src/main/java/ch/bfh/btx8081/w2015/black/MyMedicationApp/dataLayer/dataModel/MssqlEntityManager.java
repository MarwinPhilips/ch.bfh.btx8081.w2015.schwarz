package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Base class for all connections with the database. All repositories use this class, which mainly
 * provides the EntityManagerFactory emf. The emf is initialized at the start of the webserver and destroyed
 * if it shuts down. 
 * @author Marwin
 *
 */
@WebListener
public class MssqlEntityManager implements ServletContextListener {

    private static EntityManagerFactory emf;
    /**
     * Initializes the EntityManagerFactory for our Medication-Context.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        emf = Persistence.createEntityManagerFactory("Medication");
    }
    /**
     * If the Webserver shuts down, we close all running transactions and close the emf
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        emf.close();
    }
	/**
	 * Getter for the 
	 * @return EntityManagerFactory emf
	 */
    public static EntityManager createEntityManager() {
    	if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return emf.createEntityManager();
    }

}
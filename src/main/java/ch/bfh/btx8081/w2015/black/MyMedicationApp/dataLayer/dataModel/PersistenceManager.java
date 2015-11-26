package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceManager {
	
	private static String PERSISTENCE_UNIT_NAME = "Medication";
	private EntityManager em = null;
	public PersistenceManager(){
		em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
	
	public EntityTransaction getTransaction(){
		return em.getTransaction();
	}
	public EntityManager getEntityManager(){
		return em;
	}

	public void closeEntityManager() {
		//em.flush();
		em.close();
	}
}

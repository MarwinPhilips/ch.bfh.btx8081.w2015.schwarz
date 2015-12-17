package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
/**
 * The MssqlRepository is the super class for all Repositories. 
 * It provides basic connection methods over the reference to the entityManager used for the transactions
 * and the current transaction.
 * @author Marwin
 *
 */
public class MssqlRepository {
	protected EntityManager em = null;
	EntityTransaction transaction = null;
	/**
	 * Initializes the EntityManager.
	 */
	public MssqlRepository(){
		em = MssqlEntityManager.createEntityManager();
	}
	/**
	 * Gets a transaction and begins it.
	 */
	protected void beginTransaction(){
		transaction = em.getTransaction();
		transaction.begin();
	}
	/**
	 * commit the current transaction. You need to get a new one after this method.
	 */
	protected void commitTransaction(){
		transaction.commit();
	}
}

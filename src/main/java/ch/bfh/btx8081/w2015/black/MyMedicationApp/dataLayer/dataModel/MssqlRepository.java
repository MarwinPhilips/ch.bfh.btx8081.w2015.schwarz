package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IRepository;
/**
 * The MssqlRepository is the super class for all Repositories. 
 * It provides basic connection methods over the reference to the entityManager used for the transactions
 * and the current transaction.
 * @author Marwin
 *
 */
public class MssqlRepository implements IRepository {
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
	public void flush(){
		em.flush();
	}
	public Object persist(Object objectToSave){
		beginTransaction();
		em.persist(objectToSave);
		commitTransaction();
		return objectToSave;
	}
	public Object merge(Object objectToMerge){
		beginTransaction();
		objectToMerge = em.merge(objectToMerge);
		commitTransaction();
		return objectToMerge;
	}
	@Override
	public void remove(Object objectToRemove) {
		beginTransaction();
		em.remove(objectToRemove);
		commitTransaction();		
	}
	@Override
	public Object getById(Class c, int id) {
		beginTransaction();
		Object o = em.find(c, id);
		//Diese Zeile muss für Testzwecke aktiviert werden damit es keinen Cash gibt.
		//em.refresh(o);
		commitTransaction();
		return o;
	}
}

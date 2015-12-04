package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MssqlRepository {
	protected EntityManager em = null;
	EntityTransaction transaction = null;
	public MssqlRepository(){
		em = MssqlEntityManager.createEntityManager();
	}
	protected void beginTransaction(){
		transaction = em.getTransaction();
		transaction.begin();
	}
	protected void commitTransaction(){
		transaction.commit();
	}
}

package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataInterfaces.MssqlEntityManagerInterface;

public class MssqlEntityManager implements MssqlEntityManagerInterface {
	
	private static String PERSISTENCE_UNIT_NAME = "Medication";
	private static EntityManager em = null;
	public static EntityManager getEntityManager(){
		if(em==null){
			em= Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
		}
		return em;
	}
}

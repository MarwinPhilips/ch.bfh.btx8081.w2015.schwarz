package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces;

import javax.persistence.EntityManager;
/**
 *For more information look at the Classdiagram 
 * @author Marwin
 */
public interface MssqlEntityManagerInterface {
	public EntityManager getEntityManager();
}

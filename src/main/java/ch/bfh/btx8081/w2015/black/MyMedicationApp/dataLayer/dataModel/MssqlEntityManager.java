package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataInterfaces.MssqlEntityManagerInterface;

public class MssqlEntityManager implements MssqlEntityManagerInterface {

	private static String PERSISTENCE_UNIT_NAME = "Medication";

	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
				.createEntityManager();
	}
	public static EntityManager getEntityManager(String persistanceUnit) {
		return Persistence.createEntityManagerFactory(persistanceUnit)
				.createEntityManager();
	}
}

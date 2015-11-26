import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Medicament;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PersistenceManager;


public class EntityManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		PersistenceManager persistenceManager = new PersistenceManager();
		EntityTransaction transaction =persistenceManager.getTransaction();
		transaction.begin();
		Medicament medicament = new Medicament();		
		persistenceManager.getEntityManager().persist(medicament);
		medicament.setName("Dafalgan");
		transaction.commit();
		persistenceManager.closeEntityManager();
	}

}

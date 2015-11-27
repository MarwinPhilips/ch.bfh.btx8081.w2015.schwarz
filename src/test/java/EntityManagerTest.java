import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Medicament;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MssqlEntityManager;


public class EntityManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		EntityManager em = MssqlEntityManager.getEntityManager();
		EntityTransaction transaction =em.getTransaction();
		transaction.begin();
		Medicament medicament = new Medicament();		
		em.persist(medicament);
		medicament.setName("Dafalgan");
		transaction.commit();
		em.close();
	}

}

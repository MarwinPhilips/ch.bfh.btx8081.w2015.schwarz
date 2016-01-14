import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Medicament;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MssqlEntityManager;


public class EntityManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	MssqlEntityManager mem = null;
	@Before
	public void setUp() throws Exception {
		mem = new MssqlEntityManager();
		mem.contextInitialized(null);
	}
	
	@After
	public void destroy(){
		mem.contextDestroyed(null);
	}

	@Test
	public void test() {
		EntityManager em = MssqlEntityManager.createEntityManager();
		EntityTransaction transaction =em.getTransaction();
		transaction.begin();
		Medicament medicament = new Medicament();		
		em.persist(medicament);
		medicament.setName("Vicodin");
		transaction.commit();
		transaction = em.getTransaction();
		transaction.begin();
		em.remove(medicament);
		transaction.commit();
		em.close();
	}

}

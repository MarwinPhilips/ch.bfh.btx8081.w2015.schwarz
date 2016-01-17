import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationList;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationOverviewModel;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MssqlEntityManager;

public class MedicationOverviewModelTest {
	MssqlEntityManager mem = null;
	
	@Before
	public void setUp() throws Exception {
		mem = new MssqlEntityManager();
		mem.contextInitialized(null);
	}

	@After
	public void destroy() {
		mem.contextDestroyed(null);
	}

	@Test
	public void test() {
		// Loads the entityManager and fills the list.
		MedicationOverviewModel m = new MedicationOverviewModel();
		m.loadData();
		List<MedicationList> medicaments = m.getMedications();
		assertTrue(medicaments != null);
		assertTrue(medicaments.size()>0);
		
	}

}

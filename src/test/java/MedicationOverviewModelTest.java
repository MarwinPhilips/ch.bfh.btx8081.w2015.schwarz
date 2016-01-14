import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationList;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationOverviewModel;


public class MedicationOverviewModelTest {

	@Test
	public void test() {
		// Loads the entityManager and fills the list.
		MedicationOverviewModel m = new MedicationOverviewModel();
		List<MedicationList> medicaments = m.getMedications();
		assertTrue(medicaments!=null);
	}

}

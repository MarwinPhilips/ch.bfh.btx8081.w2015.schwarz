import org.junit.Test;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationOverviewModel;


public class MedicationOverviewModelTest {

	@Test
	public void test() {
		// Loads the entityManager and fills the list.
		MedicationOverviewModel m = new MedicationOverviewModel();
		m.getMedications();
	}

}

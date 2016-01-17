import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MssqlEntityManager;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionStateEnum;

public class DeletePrescriptionTest {

	PrescriptionContext px = null;
	MssqlEntityManager mem = null;
	IPrescriptionRepository prescriptionRepo = null;

	@Before
	public void setUp() throws Exception {
		mem = new MssqlEntityManager();
		mem.contextInitialized(null);
		px = new PrescriptionContext();
		prescriptionRepo = new PrescriptionRepository();

	}

	@After
	public void destroy() {
		mem.contextDestroyed(null);

	}

	@Test
	public void test() {
		Prescription prescription = px.getPrescription();
		px.save();
		px.delete();
		Prescription deletetprescription = (Prescription) prescriptionRepo
				.getById(Prescription.class, prescription.getPrescriptionId());
		assertTrue(
				"Prescription is not deleted",
				(deletetprescription.getPrescriptionState() == PrescriptionStateEnum.Deleted));

		prescriptionRepo.remove(deletetprescription);

	}

}

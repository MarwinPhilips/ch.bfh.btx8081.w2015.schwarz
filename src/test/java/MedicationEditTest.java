import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.EditPrescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionStateEnum;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MssqlEntityManager;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPrescriptionRepository;


public class MedicationEditTest {

	MssqlEntityManager mem = null;
	IPrescriptionRepository prescriptionRepo = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
		mem = new MssqlEntityManager();
		mem.contextInitialized(null);
		prescriptionRepo = new PrescriptionRepository();
	}
	
	@After
	public void destroy(){
		mem.contextDestroyed(null);
	}

	@Test
	public void test() {
		PrescriptionContext pc = new PrescriptionContext();
		Prescription prescription = pc.getPrescription();// prescriptionRepo.getNewPrescription();
		assertTrue(prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		prescription.setReserveMedication(true);
		assertTrue(prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		pc.save();
		prescriptionRepo.getById(prescription.getPrescriptionId());
		assertTrue(prescription.getPrescriptionState().equals(PrescriptionStateEnum.Running));
		
		
	}
}

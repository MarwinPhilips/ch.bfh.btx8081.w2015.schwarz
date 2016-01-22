import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionStateEnum;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MssqlEntityManager;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PrescriptionRepositoryTest;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPrescriptionRepository;


public class MedicationInsertTest {

	MssqlEntityManager mem = null;
	IPrescriptionRepository prescriptionRepo = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		mem = new MssqlEntityManager();
		mem.contextInitialized(null);
		prescriptionRepo = new PrescriptionRepositoryTest();
	}
	
	@After
	public void destroy(){
		mem.contextDestroyed(null);
	}
	
	
	/**
	 * Creating a new prescription and check if it is in the RUNNING status.
	 */
	@Test
	public void testNewMedicationInRunningStatus(){
		/*
		 * Create a new medication.
		 */
		PrescriptionContext pc = new PrescriptionContext();
		Prescription prescription = pc.getPrescription();
		pc.setPrescription(prescription);
		
		/**
		 * Test if new created medication in NEW Status is.
		 */
		assertTrue("Status is not NEW !",prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		//Add it.
		pc.save();
		/*
		 * After saving, check if this medication in Running Status is.
		 */
		assertTrue("Status is not Running !",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Running));
		pc.delete();
		prescriptionRepo.remove(prescriptionRepo.getById(Prescription.class, pc.getPrescription().getPrescriptionId()));
	}
	
}

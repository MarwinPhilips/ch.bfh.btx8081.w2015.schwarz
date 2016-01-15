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
	//Achtung um diesen Test durchzuführen muss die kommentierte Zeile in PrescriptionRepository aktiviert werden, damit der Cash ausgeschaltet wird
	public void checkStatePatternChangeToRunning() {
		PrescriptionContext pc = new PrescriptionContext();
		Prescription prescription = pc.getPrescription();
		assertTrue("Staus nach Erstellung auf NEW",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.New));
		assertTrue("Staus nach Erstellung auf NEW",prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		prescription.setReserveMedication(true);
		assertTrue("OK",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.New));
		assertTrue("OK",prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		pc.save();
		assertTrue("Status nach Speicherung ist nicht Running",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.Running));
		assertTrue("OK",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Running));
		//Clean up
		pc.delete();	
	}
	
	@Test
	//Achtung um diesen Test durchzuführen muss die kommentierte Zeile in PrescriptionRepository aktiviert werden, damit der Cash ausgeschaltet wird
	public void checkStatePatternChangeToDeleted() {
		PrescriptionContext pc = new PrescriptionContext();
		Prescription prescription = pc.getPrescription();
		assertTrue("Staus nach Erstellung auf NEW",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.New));
		assertTrue("Staus nach Erstellung auf NEW",prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		prescription.setReserveMedication(true);
		assertTrue("OK",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.New));
		assertTrue("OK",prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		pc.save();
		assertTrue("Status nach Speicherung ist nicht Running",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.Running));
		assertTrue("OK",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Running));
		//Clean up
		pc.delete();	
	}
}

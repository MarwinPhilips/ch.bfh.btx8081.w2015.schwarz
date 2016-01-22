
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

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

/**
 * Tests our Statepattern with the States which are stored in our database
 * @author Carole
 *
 */

public class StatePatternTest {

	MssqlEntityManager mem = null;
	IPrescriptionRepository prescriptionRepo = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
		mem = new MssqlEntityManager();
		mem.contextInitialized(null);

		// In this test-repository we don't use the Hibernate first level cache to be able to read the data written to the database
		// immediately after persisting an object.
		prescriptionRepo = new PrescriptionRepositoryTest();

	}
	
	@After
	public void destroy(){
		mem.contextDestroyed(null);
	}

	//this test checks if e new perscription as state new and after modification and commit State Running
	@Test
	public void checkStatePatternChangeToRunning() {
		PrescriptionContext pc = new PrescriptionContext();
		Prescription prescription = pc.getPrescription();
		//We test the Entity in the database and as well the information in our perscriotion object
		assertTrue("Staus nach Erstellung nicht auf NEW",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.New));
		assertTrue("Staus nach Erstellung nicht auf NEW",prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		prescription.setReserveMedication(true);
		assertTrue("Status sollte NEW sein, da das Objekt noch nicht Persistiert wurde",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.New));
		assertTrue("Status sollte NEW sein, da das Objekt noch nicht Persistiert wurde",prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		pc.save();
		assertTrue("Status nach Speicherung ist nicht Running",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.Running));
		assertTrue("Status nach Speicherung ist nicht Running",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Running));
		//Clean up, entity is deleted from the db, so there are no test data
		pc.delete();	
		prescriptionRepo.remove(prescriptionRepo.getById(Prescription.class, pc.getPrescription().getPrescriptionId()));
	}
	
	// not finisched jet
	
//	//This test checks if e new perscription change to the state deleted
//	@Test
//	public void checkStatePatternChangeToDeleted() {
//		PrescriptionContext pc = new PrescriptionContext();
//		Prescription prescription = pc.getPrescription();
//		assertTrue("Staus nach Erstellung nicht auf NEW",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.New));
//		assertTrue("Staus nach Erstellung nicht auf NEW",prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
//		pc.delete();
//		System.out.println(prescription.getPrescriptionState());
//		//assertTrue("Staus nach Löschen nicht auf DELETED",prescriptionRepo.getById(pc.getPrescription().getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.Deleted));
//		//assertTrue("Staus nach Löschen nicht auf DELETED",pc.getPrescription().getPrescriptionState().equals(PrescriptionStateEnum.Deleted));
//		assertTrue("Staus nach Löschen nicht auf DELETED",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Deleted));
//		
//		//Clean up, entity is removed form our Database
//		prescriptionRepo.remove(prescriptionRepo.getById(Prescription.class, pc.getPrescription().getPrescriptionId()));
//	}
	
	
}

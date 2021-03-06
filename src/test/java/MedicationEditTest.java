
import static org.junit.Assert.assertTrue;

import java.sql.Date;
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
 * This is a JUnit test class for editing a prescription
 * @author Mete, Carol
 */
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
		// In this test-repository we don't use the Hibernate first level cache to be able to read the data written to the database
		// immediately after persisting an object.
		prescriptionRepo = new PrescriptionRepositoryTest();
	}
	
	@After
	public void destroy(){
		mem.contextDestroyed(null);
	}

	
	@Test
	// Test Unit of editing of Prescription
	public void checkStatePatternChangeToEditAndEnd(){
		// Load data from prescription and test status of prescription = NEW
		PrescriptionContext pc = new PrescriptionContext();
		Prescription prescription = pc.getPrescription();
		pc.setPrescription(prescription);
		assertTrue("Staus nach Erstellung ist nicht auf NEW",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.New));
		assertTrue("Staus nach Erstellung ist nicht auf NEW",prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		prescription.setReserveMedication(true);
		assertTrue("Staus vor Editieren ist nicht auf Running",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.New));
		assertTrue("Staus vor Editieren ist nicht auf Running",prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		pc.save();
		// after pc.save() should the status of prescription = Running
		assertTrue("Status nach Speicherung ist nicht Running",prescriptionRepo.getById(prescription.getPrescriptionId()).getPrescriptionState().equals(PrescriptionStateEnum.Running));
		assertTrue("OK",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Running));
		pc.edit();
		// after pc.edit() we go to the editi View, the status of prescription should = Edit
		GregorianCalendar endDate = new GregorianCalendar();
		endDate.set(2001, 1, 1, 0, 0, 0);
		prescription.setEndDate(endDate);
		assertTrue("Staus im EditierenView ist nicht auf Edit",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Edit));
		assertTrue("Datum wurde über prescription auf 01.01.2001 nicht korrekt geändert", prescription.getEndDate().equals(endDate));
		pc.save();
		// after pc.save() should the State of the prescription = Ended. Because the endDate is expired!
		//	We have an assert because we haven't validate the Dates 
		assertTrue("EndDatum wurde überschritten und State ist nicht auf Ended",prescriptionRepo.getById(prescription.getPrescriptionId()).getEndDate().equals(PrescriptionStateEnum.Ended));
		assertTrue("EndDatum wurde überschritten und State ist nicht auf Ended",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Ended));
		assertTrue("Datum wurde über prescriptionRepo.getById auf 01.01.2001 nicht korrekt geändert",prescriptionRepo.getById(prescription.getPrescriptionId()).getEndDate().equals(endDate));
		pc.delete();
		prescriptionRepo.remove(prescriptionRepo.getById(Prescription.class, pc.getPrescription().getPrescriptionId()));
		assertTrue("OK",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Deleted));
		}
	
	
}

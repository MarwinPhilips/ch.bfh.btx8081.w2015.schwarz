
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
		prescriptionRepo.remove(prescriptionRepo.getById(Prescription.class, pc.getPrescription().getPrescriptionId()));
	}
	
	@Test
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
		prescriptionRepo.remove(prescriptionRepo.getById(Prescription.class, pc.getPrescription().getPrescriptionId()));
	}
	
	@Test
	public void checkStatePatternChangeToEditAndEnd(){
		PrescriptionContext pc = new PrescriptionContext();
		Prescription prescription = pc.getPrescription();
		pc.setPrescription(prescription);
		assertTrue("Staus vor Editieren auf Running",prescription.getPrescriptionState().equals(PrescriptionStateEnum.New));
		pc.save();
		assertTrue("Staus vor Editieren auf Running",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Running));
		pc.edit();
		GregorianCalendar endDate = new GregorianCalendar();
		endDate.set(2001, 01, 01);
		prescription.setEndDate(endDate);
		assertTrue("Staus im EditierenView auf Edit",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Edit));
		assertTrue("Datum wurde über prescription auf 01.01.2001 korrekt geändert", prescription.getEndDate().equals(endDate));
		pc.save();
//		assertTrue("EndDatum wurde überschritten und somit ist State auf Ended",prescriptionRepo.getById(prescription.getPrescriptionId()).getEndDate().equals(PrescriptionStateEnum.Ended));
//		assertTrue("EndDatum wurde überschritten und somit ist State auf Ended",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Ended));
//		assertTrue("Datum wurde über prescriptionRepo.getById auf 01.01.2001 korrekt geändert",prescriptionRepo.getById(prescription.getPrescriptionId()).getEndDate().equals(endDate));
		pc.delete();
		assertTrue("OK",prescription.getPrescriptionState().equals(PrescriptionStateEnum.Deleted));
		prescriptionRepo.remove(prescriptionRepo.getById(Prescription.class, pc.getPrescription().getPrescriptionId()));
	}
	
	
}

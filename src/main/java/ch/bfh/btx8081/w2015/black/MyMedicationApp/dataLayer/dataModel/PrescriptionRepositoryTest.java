package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionStateEnum;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPrescriptionRepository;
/**
 * All Prescription POJO need to be created, loaded and persisted over this Repository.
 * @author Carole
 */

//Diese Klasse wird ausschliesslich zu Testzwecken verwendet, Cash deaktiviert!
public class PrescriptionRepositoryTest extends MssqlRepositoryTest implements IPrescriptionRepository{

	public PrescriptionRepositoryTest() {
		super();
	}
	
		@Override
		public Prescription getById(int id) {
			beginTransaction();
			Prescription prescription = em.find(Prescription.class, id);
			em.refresh(prescription);
			commitTransaction();
			return prescription;
		}
		/**
		 *  Returns a new Prescription with no attributes except the ID which is persisted and
		 *  under the control of JPA.
		 */
		@Override
		public Prescription getNewPrescription() {
			beginTransaction();
			Prescription prescription = new Prescription();
			prescription.setPrescriptionState(PrescriptionStateEnum.New);
			em.persist(prescription);
			commitTransaction();
			return prescription;
		}
}

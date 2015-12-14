package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionStateEnum;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPrescriptionRepository;

public class PrescriptionRepository extends MssqlRepository implements IPrescriptionRepository {

	public PrescriptionRepository() {
		super();
	}
	@Override
	public Prescription getById(int id) {
		transaction=em.getTransaction();
		transaction.begin();
		Prescription prescription = em.find(Prescription.class, id);
		transaction.commit();
		return prescription;
	}
	@Override
	public Prescription getNewPrescription() {
		transaction=em.getTransaction();
		transaction.begin();
		Prescription prescription = new Prescription();
		prescription.setPrescriptionState(PrescriptionStateEnum.New);
		em.persist(prescription);
		transaction.commit();
		return prescription;
	}
}

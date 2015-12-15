package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.DosisScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionStateEnum;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IDosisSchemeRepository;

public class DosisSchemeRepository extends MssqlRepository implements IDosisSchemeRepository {

	public DosisSchemeRepository() {
		super();
	}
	@Override
	public DosisScheme getNewDosisScheme() {
		transaction=em.getTransaction();
		transaction.begin();
		DosisScheme dosisScheme = new DosisScheme();
		em.persist(dosisScheme);
		transaction.commit();
		return dosisScheme;
	}

}

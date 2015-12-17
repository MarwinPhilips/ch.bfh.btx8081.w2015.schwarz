package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.DosisScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionStateEnum;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IDosisSchemeRepository;
/**
 * All DosisScheme POJO need to be created, loaded and persisted over this Repository.
 * @author Marwin
 */
public class DosisSchemeRepository extends MssqlRepository implements IDosisSchemeRepository {

	
	public DosisSchemeRepository() {
		super();
	}
	/**
	 * Returns a new DosisScheme with no attributes except the ID which is persisted and
	 * under the control of JPA.
	 */
	@Override
	public DosisScheme getNewDosisScheme() {
		beginTransaction();
		DosisScheme dosisScheme = new DosisScheme();
		em.persist(dosisScheme);
		commitTransaction();
		return dosisScheme;
	}

}

package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
/**
 *For more information look at the Classdiagram 
 * @author Marwin
 */
public interface IPrescriptionRepository extends IRepository {
	public Prescription getById(int id);
	public Prescription getNewPrescription();
}

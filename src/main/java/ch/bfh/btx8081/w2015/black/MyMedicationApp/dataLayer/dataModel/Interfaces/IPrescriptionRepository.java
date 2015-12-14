package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;

public interface IPrescriptionRepository {
	public Prescription getById(int id);
	public Prescription getNewPrescription();
}

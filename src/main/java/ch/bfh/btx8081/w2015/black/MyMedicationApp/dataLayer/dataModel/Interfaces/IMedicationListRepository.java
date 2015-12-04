package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces;

import java.util.List;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationList;

public interface IMedicationListRepository {
	public List<MedicationList> loadMedications(int personId);
}
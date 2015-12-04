package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;
import java.util.Observable;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MedicationListRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IMedicationListRepository;

public class MedicationOverviewModel extends Observable {
	private List<MedicationList> medications = null;
	private IMedicationListRepository repo = null;
	public MedicationOverviewModel(){
		repo = new MedicationListRepository();		
	}
	public void loadData(){
		// currently static PersonId
		medications = repo.loadMedications(1);
		setChanged();
		notifyObservers(null);
	}
	public List<MedicationList> getMedications() {
		return medications;
	}
}

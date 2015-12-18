package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;
import java.util.Observable;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MedicationListRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IMedicationListRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPrescriptionRepository;

public class MedicationOverviewModel extends Observable {
	private List<MedicationList> medications = null;
	private IMedicationListRepository medicationListRepo = null;
	private PrescriptionContext prescriptionContext = null;
	private IPrescriptionRepository prescriptionRepo = null;
	public MedicationOverviewModel(){
		medicationListRepo = new MedicationListRepository();	
		prescriptionContext = new PrescriptionContext();
		prescriptionRepo = new PrescriptionRepository();
	}
	public void loadData(){
		// currently static PersonId
		medications = medicationListRepo.loadMedications(1);
		setChanged();
		notifyObservers(null);
	}
	public List<MedicationList> getMedications() {
		return medications;
	}

	public Prescription getSelectedPrescription() {
	    return prescriptionContext.getPrescription();
	}
	public void setSelectedPrescription(Prescription selectedPrescription) {
		Prescription p = prescriptionRepo.getById(selectedPrescription.getPrescriptionId());
		prescriptionContext.setPrescription(p);
	}
	public void deletePrescription() {
		prescriptionContext.delete();
	    loadData();
	}
	public boolean canModifyPrescription(){
		return prescriptionContext.canModify();
	}
	public PrescriptionContext getPrescriptionContext(){
		return prescriptionContext;
	}
}

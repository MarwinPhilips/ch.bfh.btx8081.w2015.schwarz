package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;

import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MedicationListRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IMedicationListRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view.MedicationOverView;

public class MedicationOverviewModel extends Observable {
	private List<MedicationList> medications = null;
	private IMedicationListRepository medicationListRepo = null;
	private IPrescriptionRepository prescriptionRepo = null;
	private Prescription selectedPrescription = null;
	public MedicationOverviewModel(){
		medicationListRepo = new MedicationListRepository();		
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
	    return selectedPrescription;
	}
	public void setSelectedPrescription(Prescription selectedPrescription) {
	    this.selectedPrescription = selectedPrescription;
	}
	public void deletePrescription() {
	    selectedPrescription.setDeleted(true);
	    loadData();
	}
}

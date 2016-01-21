package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;
import java.util.Observable;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MedicationListRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IMedicationListRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPrescriptionRepository;
/**
 * The medicationOverviewModel is used to display existing prescriptions and to handle a prescriptionContext, 
 * which stores the actually chosen prescription. 
 * @author Marwin, Michel
 *
 */
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
	/**
	 * Loads all Prescriptions of the logged in person.
	 */
	public void loadData(){
		//  static PersonId 1 because we only have 1 imaginary user.
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
		prescriptionContext.setPrescription(null);
	    loadData();
	}
	public boolean canModifyPrescription(){
		return prescriptionContext.canModify();
	}
	public PrescriptionContext getPrescriptionContext(){
		return prescriptionContext;
	}
	public void edit(){
		prescriptionContext.edit();
	}
}

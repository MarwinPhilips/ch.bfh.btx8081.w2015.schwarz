package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;
import java.util.Observable;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.TimeSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.ITimeSchemeRepository;

public class MedicationEditModel extends Observable {
	private Prescription prescription = null;
	private List<TimeScheme> timeSchemes = null;
	private ITimeSchemeRepository timeSchemeRepo = null;
	private IPrescriptionRepository prescriptionRepo = null;
	
	public MedicationEditModel(){
		timeSchemeRepo = new TimeSchemeRepository();
		prescriptionRepo = new PrescriptionRepository();
	}

	public void loadData(){
		prescription = prescription == null ? null : prescriptionRepo.getById(prescription.getPrescriptionId());
		timeSchemes = timeSchemeRepo.getAllTimeschemes();
		setChanged();
		notifyObservers(null);
	}
	public void setPrescriptionId(int prescriptionId){
		prescription = prescriptionRepo.getById(prescriptionId);
	}
	
	public Prescription getPrescription() {
		return prescription;
	}


	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}


	public List<TimeScheme> getTimeSchemes() {
		return timeSchemes;
	}


	public void setTimeSchemes(List<TimeScheme> timeSchemes) {
		this.timeSchemes = timeSchemes;
	}
}

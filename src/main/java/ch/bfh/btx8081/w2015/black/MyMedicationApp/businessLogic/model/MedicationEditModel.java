package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;
import java.util.Observable;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.TimeSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.ITimeSchemeRepository;

public class MedicationEditModel extends Observable {
	private List<TimeScheme> timeSchemes = null;
	private ITimeSchemeRepository timeSchemeRepo = null;
	private PrescriptionContext context = null;
	
	public MedicationEditModel(){
		timeSchemeRepo = new TimeSchemeRepository();
		context = new PrescriptionContext();
	}

	public void loadData(){
		timeSchemes = timeSchemeRepo.getAllTimeschemes();
		setChanged();
		notifyObservers(null);
	}
	public void setPrescriptionContext(PrescriptionContext context){
		this.context=context;
	}
	
	public Prescription getPrescription() {
		return context.getPrescription();
	}


	public void setPrescription(Prescription prescription) {
		context.setPrescription(prescription);
	}


	public List<TimeScheme> getTimeSchemes() {
		return timeSchemes;
	}


	public void setTimeSchemes(List<TimeScheme> timeSchemes) {
		this.timeSchemes = timeSchemes;
	}
}

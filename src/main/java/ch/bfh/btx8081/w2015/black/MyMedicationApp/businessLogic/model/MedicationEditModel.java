package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;
import java.util.Observable;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.DosisSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.TimeSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IDosisSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.ITimeSchemeRepository;

public class MedicationEditModel extends Observable {
	private List<TimeScheme> timeSchemes = null;
	private ITimeSchemeRepository timeSchemeRepo = null;
	private IDosisSchemeRepository dosisSchemeRepo = null;
	private PrescriptionContext context = null;
	
	public MedicationEditModel(){
		timeSchemeRepo = new TimeSchemeRepository();
		context = new PrescriptionContext();
		dosisSchemeRepo = new DosisSchemeRepository();
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


	public void setPrescriptionTimeScheme(TimeScheme timeScheme) {
		Prescription prescription = context.getPrescription();
		prescription.setTimeScheme(timeScheme);
		prescription.getDosisSchemes().clear();
		for(TimeSchemeTime timeSchemeTime : timeScheme.getTimeSchemeTimes()){
			DosisScheme d = dosisSchemeRepo.getNewDosisScheme();
			d.setPrescription(prescription);
			prescription.getDosisSchemes().add(d);
			d.setDosisSchemeName(timeSchemeTime.getTimeSchemeTimeName());
			d.setQuantityUnit(prescription.getMedicament().getQuantityUnit());
			d.setTime(timeSchemeTime.getTimespan());
		}
			
	}
}

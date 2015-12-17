package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;
import java.util.Observable;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.DosisSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MethodOfApplicationRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PersonRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.TimeSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.WayOfApplicationRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IDosisSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IMethodOfApplicationRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPersonRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.ITimeSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IWayOfApplicationRepository;

public class MedicationEditModel extends Observable {
	private List<TimeScheme> timeSchemes = null;
	private List<WayOfApplication> wayOfApplications = null;
	private List<MethodOfApplication> methodOfApplications = null;
	private ITimeSchemeRepository timeSchemeRepo = null;
	private IDosisSchemeRepository dosisSchemeRepo = null;
	private PrescriptionContext context = null;
	private IMethodOfApplicationRepository methodOfApplicationRepo;
	private IWayOfApplicationRepository wayOfApplicationRepo;
	private IPersonRepository personRepo;
	private Person loggedInPerson = null;

	public MedicationEditModel() {
		timeSchemeRepo = new TimeSchemeRepository();
		context = new PrescriptionContext();
		dosisSchemeRepo = new DosisSchemeRepository();
		methodOfApplicationRepo = new MethodOfApplicationRepository();
		wayOfApplicationRepo = new WayOfApplicationRepository();
		personRepo = new PersonRepository();
	}

	public void loadData() {
		timeSchemes = timeSchemeRepo.getAllTimeschemes();
		methodOfApplications = methodOfApplicationRepo
				.getAllMethodOfApplication();
		wayOfApplications = wayOfApplicationRepo.getAllWayOfApplication();
		setChanged();
		notifyObservers(null);
	}

	public void setPrescriptionContext(PrescriptionContext context) {
		this.context = context;
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
	
	public void save() {
		context.save();
	}

	public void setPrescriptionTimeScheme(TimeScheme timeScheme) {
		Prescription prescription = context.getPrescription();
		if(prescription.getDosisSchemes()!=null){
			for (DosisScheme d : prescription.getDosisSchemes()) {
				dosisSchemeRepo.remove(d);
			}
			prescription.getDosisSchemes().clear();
		}
		
		prescription.setTimeScheme(timeScheme);
		//prescription.getDosisSchemes().clear();
		for (TimeSchemeTime timeSchemeTime : timeScheme.getTimeSchemeTimes()) {
			DosisScheme d = new DosisScheme();
			d.setPrescription(prescription);
			prescription.getDosisSchemes().add(d);
			d.setDosisSchemeName(timeSchemeTime.getTimeSchemeTimeName());
			d.setQuantityUnit(prescription.getMedicament().getQuantityUnit());
			d.setTime(timeSchemeTime.getTimespan());
			dosisSchemeRepo.persist(d);
		}
	}

	public List<WayOfApplication> getWayOfApplications() {
		return wayOfApplications;
	}

	public List<MethodOfApplication> getMethodOfApplications() {
		return methodOfApplications;
	}

	public Person getLoggedInPerson() {
		if (loggedInPerson == null) {
			// We currently only have one Person, so it is the one to be.
			loggedInPerson = (Person) personRepo.getById(Person.class, 1);
		}
		return loggedInPerson;

	}
}

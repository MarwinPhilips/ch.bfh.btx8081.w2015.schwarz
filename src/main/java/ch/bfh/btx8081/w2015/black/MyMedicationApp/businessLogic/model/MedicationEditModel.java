package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.DosisSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MedicamentRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MethodOfApplicationRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PersonRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.TimeSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.WayOfApplicationRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IDosisSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IMedicamentRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IMethodOfApplicationRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPersonRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.ITimeSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IWayOfApplicationRepository;

public class MedicationEditModel extends Observable {
	private List<TimeScheme> timeSchemes = null;
	private List<WayOfApplication> wayOfApplications = null;
	private List<MethodOfApplication> methodOfApplications = null;
	private List<Medicament> medicaments = null;
	private ITimeSchemeRepository timeSchemeRepo = null;
	private IDosisSchemeRepository dosisSchemeRepo = null;
	private PrescriptionContext context = null;
	private IMethodOfApplicationRepository methodOfApplicationRepo;
	private IWayOfApplicationRepository wayOfApplicationRepo;
	private IPersonRepository personRepo;
	private IMedicamentRepository mediRepo;
	private Person loggedInPerson = null;

	public MedicationEditModel() {
		timeSchemeRepo = new TimeSchemeRepository();
		context = new PrescriptionContext();
		dosisSchemeRepo = new DosisSchemeRepository();
		methodOfApplicationRepo = new MethodOfApplicationRepository();
		wayOfApplicationRepo = new WayOfApplicationRepository();
		personRepo = new PersonRepository();
		mediRepo = new MedicamentRepository();		
	}

	public void loadData() {
		timeSchemes = timeSchemeRepo.getAllTimeschemes();
		methodOfApplications = methodOfApplicationRepo
				.getAllMethodOfApplication();
		wayOfApplications = wayOfApplicationRepo.getAllWayOfApplication();
		medicaments = mediRepo.getAllMedicaments();
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
				DosisScheme toDel = (DosisScheme) dosisSchemeRepo.getById(DosisScheme.class,d.getDosisSchemeId());
				dosisSchemeRepo.remove(toDel);
			}
			prescription.getDosisSchemes().clear();
		}
		
		prescription.setTimeScheme(timeScheme);
		List<TimeSchemeTime> timeSchemeTimes = timeScheme.getTimeSchemeTimes();
		Collections.sort(timeSchemeTimes, new Comparator<TimeSchemeTime>() {
			@Override
			public int compare(TimeSchemeTime o1, TimeSchemeTime o2) {
				return o1.getTimespan().compareTo(o2.getTimespan());
			}
        });

		if(prescription.getDosisSchemes()!=null){
			prescription.getDosisSchemes().clear();
		}
		for (TimeSchemeTime timeSchemeTime : timeSchemeTimes) {
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
	
	public List<Medicament> getMedicaments(){
		return medicaments;
	}
	public Person getLoggedInPerson() {
		if (loggedInPerson == null) {
			// We currently only have one Person, so it is the one to be.
			loggedInPerson = (Person) personRepo.getById(Person.class, 1);
		}
		return loggedInPerson;

	}
	public void saveDosisScheme(DosisScheme d){
		dosisSchemeRepo.persist(d);
	}

	public void resetPrescription() {
		context = new PrescriptionContext();
	}
}

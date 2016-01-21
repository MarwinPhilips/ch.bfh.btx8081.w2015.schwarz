package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates;

import java.util.GregorianCalendar;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.PrescriptionRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IPrescriptionRepository;

/**
 * The Context for the PrescriptionStates. It provides all Methods which can be
 * called on a PrescriptionState. Initializes the correct State on
 * setPrescription. Please see the Diagram in documents/task12 folder for UML
 * Diagram and State Diagram managed with this class.
 * 
 * @author Marwin
 *
 */
public class PrescriptionContext {
	private PrescriptionState prescriptionState;
	private IPrescriptionRepository repo;

	public PrescriptionContext() {
		this.repo = new PrescriptionRepository();
	}

	/**
	 * Only used within this package, nobody else can change the state. Every
	 * time you change the state you must change the State in this class over
	 * this method.
	 * 
	 * @param newState
	 */
	protected void setPrescriptionState(PrescriptionState newState) {
		prescriptionState = newState;
	}

	/**
	 * In any state you can load the prescription.
	 * 
	 * @return
	 */
	public Prescription getPrescription() {
		Prescription p;
		if (prescriptionState == null) {
			p = new Prescription();
			p.setPrescriptionState(PrescriptionStateEnum.New);
			setPrescription(p);
			repo.persist(p);
			return p;
		}
		p = repo.getById(prescriptionState.getPrescription()
				.getPrescriptionId());
		prescriptionState.setPrescription(p);
		return p;
	}

	/**
	 * Sets the prescription and initializes the correct prescriptionState.
	 * 
	 * @param prescription
	 */
	public void setPrescription(Prescription prescription) {
		if (prescription == null) {
			prescriptionState = null;
		} else {
			switch (prescription.getPrescriptionState()) {
			// Since we don't now the State of the prescription
			// we have to check it over the enum.
			case New:
				prescriptionState = new NewPrescription(this, prescription);
				break;
			case Running:
				prescriptionState = new RunningPrescription(this, prescription);
				break;
			case Deleted:
				prescriptionState = new DeletedPrescription(this, prescription);
				break;
			case Edit:
				prescriptionState = new EditPrescription(this, prescription);
				break;
			case Ended:
				prescriptionState = new EndedPrescription(this, prescription,
						prescription.getEndDate());
				break;
			default:
				System.out.println("The Prescrtiption  with ID "
						+ prescription.getPrescriptionId()
						+ " has no valid state set.");
			}
		}
	}

	public void delete() {
		prescriptionState.delete();
	}

	public boolean canModify() {
		return prescriptionState.canModify();
	}

	public void edit() {
		prescriptionState.edit();
	}

	public void save() {
		prescriptionState.save();
	}

	public void abort() {
		prescriptionState.abort();
	}

	public void stop(GregorianCalendar endDate) {
		prescriptionState.stop(endDate);
	}

	protected void setPrescriptionState(PrescriptionStateEnum state) {
		prescriptionState.getPrescription().setPrescriptionState(state);
		repo.merge(prescriptionState.getPrescription());
	}

	protected void setPrescriptionState(PrescriptionStateEnum state,
			boolean persist) {
		prescriptionState.getPrescription().setPrescriptionState(state);
		if (persist) {
			repo.persist(prescriptionState.getPrescription());
		} else {
			repo.merge(prescriptionState.getPrescription());
		}
	}
}

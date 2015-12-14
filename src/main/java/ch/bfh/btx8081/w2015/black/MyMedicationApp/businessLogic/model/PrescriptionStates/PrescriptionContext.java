package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates;

import java.util.GregorianCalendar;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;

public class PrescriptionContext {
	private PrescriptionState prescriptionState;
	public PrescriptionContext() {
	}
	protected void setPrescriptionState(PrescriptionState newState) {
		prescriptionState = newState;
	}
	public Prescription getPrescription(){
		return prescriptionState.getPrescription();
	}
	//Since we don't now the State of the prescription
	// we have to check it over the enum.
	public void setPrescription(Prescription prescription){
		switch (prescription.getPrescriptionState()) {
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
			prescriptionState = new EndedPrescription(this, prescription, prescription.getEndDate());
			break;
		default:
			System.out.println("The Prescrtiption  with ID "
					+ prescription.getPrescriptionId()
					+ " has no valid state set.");
		}
	}
	public void delete() {
		prescriptionState.delete();
	}
	public boolean canModify(){
		return prescriptionState.canModify();
	}
	public void edit(){
		prescriptionState.edit();
	}
	public void save(){
		prescriptionState.save();
	}
	public void abort(){
		prescriptionState.abort();
	}
	public void stop(GregorianCalendar endDate){
		prescriptionState.stop(endDate);
	}
}

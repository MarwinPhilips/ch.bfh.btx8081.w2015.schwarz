package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates;

import java.util.GregorianCalendar;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;

public class EndedPrescription extends PrescriptionState {

	public EndedPrescription(PrescriptionContext context, Prescription prescription, GregorianCalendar endDate) {
		super(context, prescription);
		prescription.setEndDate(endDate);
		context.setPrescriptionState(PrescriptionStateEnum.Ended);
	}
	public void delete(){
		context.setPrescriptionState(new DeletedPrescription(context, prescription));
	}
	public void edit(){
		context.setPrescriptionState(new EditPrescription(context, prescription));
	}
	// An ended Medicament can be modified.
	@Override
	public boolean canModify(){
		return true;
	}
}

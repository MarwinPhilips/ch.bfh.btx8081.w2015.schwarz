package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates;

import java.util.GregorianCalendar;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;

public class RunningPrescription extends PrescriptionState {
	public RunningPrescription(PrescriptionContext context, Prescription prescription) {
		super(context,prescription);
		prescription.setPrescriptionState(PrescriptionStateEnum.Running);
	}
	@Override
	public void delete() {
		context.setPrescriptionState(new DeletedPrescription(context, prescription));
	}
	@Override
	public void stop(GregorianCalendar endDate) {
		context.setPrescriptionState(new EndedPrescription(context,prescription,endDate));
	}
	@Override
	public boolean canModify(){
		return true;
	}
	@Override
	public void edit(){
		context.setPrescriptionState(new EditPrescription(context,prescription));
	}
}

package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates;

import java.util.GregorianCalendar;
/**
 * @author Marwin
 */

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;

public class RunningPrescription extends PrescriptionState {
	public RunningPrescription(PrescriptionContext context, Prescription prescription) {
		super(context,prescription);
	}
	@Override
	public void delete() {
		context.setPrescriptionState(PrescriptionStateEnum.Deleted);
		context.setPrescriptionState(new DeletedPrescription(context, prescription));
	}
	@Override
	public void stop(GregorianCalendar endDate) {
		context.setPrescriptionState(PrescriptionStateEnum.Ended);
		context.setPrescriptionState(new EndedPrescription(context,prescription,endDate));
	}
	@Override
	public boolean canModify(){
		return true;
	}
	@Override
	public void edit(){
		context.setPrescriptionState(PrescriptionStateEnum.Edit);
		context.setPrescriptionState(new EditPrescription(context,prescription));
	}
}

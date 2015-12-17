package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;

public class EditPrescription extends PrescriptionState {

	public EditPrescription(PrescriptionContext context,Prescription prescription) {
		super(context, prescription);
		context.setPrescriptionState(PrescriptionStateEnum.Running);
	}
	@Override
	public void save(){
		context.setPrescriptionState(new RunningPrescription(context, prescription));
	}
	@Override
	public void abort(){
		
		context.setPrescriptionState(new RunningPrescription(context, prescription));
	}
}

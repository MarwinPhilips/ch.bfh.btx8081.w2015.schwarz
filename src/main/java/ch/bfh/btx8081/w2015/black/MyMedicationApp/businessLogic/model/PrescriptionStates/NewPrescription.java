package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;

public class NewPrescription extends PrescriptionState {
	
	public NewPrescription(PrescriptionContext context, Prescription prescription) {
		super(context,prescription);
	}
	@Override
	public void save() {	
		context.setPrescriptionState(PrescriptionStateEnum.Running, true);
		context.setPrescriptionState(new RunningPrescription(context,prescription));
	}
	@Override
	public void abort() {
		context.setPrescriptionState(PrescriptionStateEnum.Deleted, true);		
		context.setPrescriptionState(new DeletedPrescription(context, prescription));
	}

}

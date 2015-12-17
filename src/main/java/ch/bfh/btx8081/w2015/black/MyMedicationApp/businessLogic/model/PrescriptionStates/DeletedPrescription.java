package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;

public class DeletedPrescription extends PrescriptionState {

	public DeletedPrescription(PrescriptionContext context,	Prescription prescription) {
		super(context, prescription);
		prescription.setDeleted(true);
		context.setPrescriptionState(PrescriptionStateEnum.Deleted);
	}
}

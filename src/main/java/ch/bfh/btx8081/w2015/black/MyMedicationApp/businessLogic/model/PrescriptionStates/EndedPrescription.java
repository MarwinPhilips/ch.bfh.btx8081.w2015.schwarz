package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates;

import java.util.GregorianCalendar;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
/**
 *More Information see PerscriptionContext
 * 
 * @author Marwin
 */
public class EndedPrescription extends PrescriptionState {

	public EndedPrescription(PrescriptionContext context, Prescription prescription, GregorianCalendar endDate) {
		super(context, prescription);
		prescription.setEndDate(endDate);
	}
	public void delete(){
		context.setPrescriptionState(PrescriptionStateEnum.Deleted);
		context.setPrescriptionState(new DeletedPrescription(context, prescription));
	}
	public void edit(){
		context.setPrescriptionState(PrescriptionStateEnum.Edit);
		context.setPrescriptionState(new EditPrescription(context, prescription));
	}
	// An ended Medicament can be modified.
	@Override
	public boolean canModify(){
		return true;
	}
}

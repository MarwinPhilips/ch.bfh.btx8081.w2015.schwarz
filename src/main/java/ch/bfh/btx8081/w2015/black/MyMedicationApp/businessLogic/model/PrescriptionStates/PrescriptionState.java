package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates;

import java.util.GregorianCalendar;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;

public abstract class PrescriptionState {
	protected Prescription prescription = null;
	protected PrescriptionContext context = null;
	public PrescriptionState(PrescriptionContext context, Prescription prescription){
		this.context = context;
		this.prescription=prescription;
	}
	public void save(){
		System.out.println("save is not Supported by this State");
	}
	public void delete() {
		System.out.println("delete is not Supported by this State");
	}
	public void stop(GregorianCalendar endDate) {
		System.out.println("stop is not Supported by this State");
	}
	public void abort() {
		System.out.println("abort is not Supported by this State");
	}
	public void edit(){		
		System.out.println("edit is not Supported by this State");
	}
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	public PrescriptionContext getContext() {
		return context;
	}
	public void setContext(PrescriptionContext context) {
		this.context = context;
	}
	public boolean canModify() {
		return false;
	}
}

package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.Observable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;
/**
 *For more information look at the Classdiagram 
 * @author Marwin
 */

@Entity
@Table(name="V_MEDICATIONOVERVIEW")
public class MedicationList extends Observable {
	@ManyToOne
	private Person person;
	// The prescription is unique in the view
	@Id
	@ManyToOne
	private Prescription prescription;
	//@Size(min=1, max=4, message="Should be between 2 and 4 c")
	@NotNull(message="Please enter a name")
	private String medicamentname;
	// The dose is not saved in one attribute, so for the moment we let it away.
	//private String dose;
	private String timeschemename;
	private String prescriptioncomment;
	private boolean reservemedication;
	
	public MedicationList(){
		
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	public String getMedicamentname() {
		return medicamentname;
	}
	public void setMedicamentname(String medicamentname) {
		this.medicamentname = medicamentname;
	}
//	public String getDose() {
//		return dose;
//	}
//	public void setDose(String dose) {
//		this.dose = dose;
//	}
	public String getTimeschemename() {
		return timeschemename;
	}
	public void setTimeschemename(String timeschemename) {
		this.timeschemename = timeschemename;
	}
	public String getPrescriptioncomment() {
		return prescriptioncomment;
	}
	public void setPrescriptioncomment(String prescriptioncomment) {
		this.prescriptioncomment = prescriptioncomment;
	}
	public boolean isReservemedication() {
		return reservemedication;
	}
	public void setReservemedication(boolean reservemedication) {
		this.reservemedication = reservemedication;
	}
	
}

package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int prescriptionId = 0;	
	@ManyToOne
	private Person person = null;
	@ManyToOne
	private Medicament medicament = null;
	@ManyToOne
	private MethodOfApplication methodOfApplication = null;	
	@ManyToOne
	private WayOfApplication wayOfApplication = null;
	private GregorianCalendar startDate = null;
	private GregorianCalendar endDate = null;
	private boolean selfMedication= false;
	private boolean reserveMedication = false;
	@OneToMany(mappedBy="Prescription")	
	private ArrayList<DosisScheme> dosisSchemes = null;
	private String comment = null;
	public Prescription(){
		
	}
	public int getPrescriptionId(){
		return prescriptionId;
	}
	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Medicament getMedicament() {
		return medicament;
	}
	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}
	public MethodOfApplication getMethodOfApplication() {
		return methodOfApplication;
	}
	public void setMethodOfApplication(MethodOfApplication methodOfApplication) {
		this.methodOfApplication = methodOfApplication;
	}
	public WayOfApplication getWayOfApplication() {
		return wayOfApplication;
	}
	public void setWayOfApplication(WayOfApplication wayOfApplication) {
		this.wayOfApplication = wayOfApplication;
	}
	public GregorianCalendar getStartDate() {
		return startDate;
	}
	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}
	public GregorianCalendar getEndDate() {
		return endDate;
	}
	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}
	public boolean isSelfMedication() {
		return selfMedication;
	}
	public void setSelfMedication(boolean selfMedication) {
		this.selfMedication = selfMedication;
	}
	public boolean isReserveMedication() {
		return reserveMedication;
	}
	public void setReserveMedication(boolean reserveMedication) {
		this.reserveMedication = reserveMedication;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public ArrayList<DosisScheme> getDosisSchemes() {
		return dosisSchemes;
	}
	public void setDosisSchemes(ArrayList<DosisScheme> dosisSchemes) {
		this.dosisSchemes = dosisSchemes;
	}
}

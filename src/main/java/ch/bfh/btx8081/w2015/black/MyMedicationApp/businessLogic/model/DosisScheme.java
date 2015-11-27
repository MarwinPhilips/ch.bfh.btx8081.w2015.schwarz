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
public class DosisScheme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int DosisSchemeId = 0;
	@ManyToOne
	private Prescription prescription = null;
	@ManyToOne
	private TimeScheme timeScheme = null;
	@OneToMany(mappedBy = "DosisScheme")
	private ArrayList<ActivityFeedback> activityFeedbacks = null;
	// ToDO: Zeitpunkt: Timespan
	private double amount = 0.0;
	private String quantityUnit = null;
	private GregorianCalendar validFrom = null;
	private GregorianCalendar validTo = null;
	public DosisScheme(){
		
	}
	public int getDosisSchemeId() {
		return DosisSchemeId;
	}
	public void setDosisSchemeId(int dosisSchemeId) {
		DosisSchemeId = dosisSchemeId;
	}
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	public TimeScheme getTimeScheme() {
		return timeScheme;
	}
	public void setTimeScheme(TimeScheme timeScheme) {
		this.timeScheme = timeScheme;
	}
	public ArrayList<ActivityFeedback> getActivityFeedbacks() {
		return activityFeedbacks;
	}
	public void setActivityFeedbacks(ArrayList<ActivityFeedback> activityFeedbacks) {
		this.activityFeedbacks = activityFeedbacks;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	public GregorianCalendar getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(GregorianCalendar validFrom) {
		this.validFrom = validFrom;
	}
	public GregorianCalendar getValidTo() {
		return validTo;
	}
	public void setValidTo(GregorianCalendar validTo) {
		this.validTo = validTo;
	}
	
}

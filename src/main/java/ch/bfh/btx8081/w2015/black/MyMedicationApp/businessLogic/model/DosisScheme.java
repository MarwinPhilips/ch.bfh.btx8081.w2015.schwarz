package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Entity
public class DosisScheme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int DosisSchemeId = 0;
	@ManyToOne
	private Prescription prescription = null;
	@OneToMany(mappedBy = "dosisScheme")
	private ArrayList<ActivityFeedback> activityFeedbacks = null;
	// ToDO: Zeitpunkt: Timespan
	private double amount = 0.0;
	/**
	 * Duration can not be persisted. Therefore we persist it as a string with the correlated parsing :(
	 * See: http://stackoverflow.com/questions/28427525/how-to-model-java-time-duration-in-mysql-database
	 */
	@Transient
	private Duration time = null;
	private String time_string = null;
	private String quantityUnit = null;
	private GregorianCalendar validFrom = null;
	private GregorianCalendar validTo = null;
	private String dosisSchemeName = null;
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
	public String getDosisSchemeName() {
		return dosisSchemeName;
	}
	public void setDosisSchemeName(String dosisSchemeName) {
		this.dosisSchemeName = dosisSchemeName;
	}
	@PostLoad
	public void init() {
	  this.time = this.time_string == null ? null : Duration.parse(this.time_string);
	};
	public void setTime(Duration time) {
	  this.time_string = time == null ? null : time.toString();
	  this.time=time;
	}
	public Duration getTimespan() {
		return time;
	}
}

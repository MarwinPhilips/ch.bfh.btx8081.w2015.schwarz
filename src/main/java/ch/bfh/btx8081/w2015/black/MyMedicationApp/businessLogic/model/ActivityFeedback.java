package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class ActivityFeedback {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int activityFeedbackId = 0;
	@ManyToOne
	private DosisScheme dosisScheme = null;	
	private GregorianCalendar creationTime = null;
	private GregorianCalendar timeSchemeFeedbackTime = null;
	private boolean applied = false;
	private String note = null;
	public ActivityFeedback(){
		
	}
	public int getActivityFeedbackId() {
		return activityFeedbackId;
	}
	public void setActivityFeedbackId(int activityFeedbackId) {
		this.activityFeedbackId = activityFeedbackId;
	}
	public DosisScheme getDosisScheme() {
		return dosisScheme;
	}
	public void setDosisScheme(DosisScheme dosisScheme) {
		this.dosisScheme = dosisScheme;
	}
	public GregorianCalendar getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(GregorianCalendar creationTime) {
		this.creationTime = creationTime;
	}
	public GregorianCalendar getTimeSchemeFeedbackTime() {
		return timeSchemeFeedbackTime;
	}
	public void setTimeSchemeFeedbackTime(GregorianCalendar timeSchemeFeedbackTime) {
		this.timeSchemeFeedbackTime = timeSchemeFeedbackTime;
	}
	public boolean isApplied() {
		return applied;
	}
	public void setApplied(boolean applied) {
		this.applied = applied;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}

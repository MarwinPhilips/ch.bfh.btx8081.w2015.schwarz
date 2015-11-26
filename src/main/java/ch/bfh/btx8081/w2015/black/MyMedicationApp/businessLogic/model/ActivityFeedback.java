package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

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
	
	public ActivityFeedback(){
		
	}
}

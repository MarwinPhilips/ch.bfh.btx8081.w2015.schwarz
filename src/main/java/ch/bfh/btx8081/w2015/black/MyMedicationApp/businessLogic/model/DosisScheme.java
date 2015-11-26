package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.ArrayList;

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
	public DosisScheme(){
		
	}
	
}

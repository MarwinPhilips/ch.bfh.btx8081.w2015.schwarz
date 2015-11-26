package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TimeScheme {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int timeSchemeId = 0;
	@OneToMany(mappedBy="TimeScheme")
	private ArrayList<DosisScheme> dosisSchemes = null;
	public TimeScheme(){
		
	}
}

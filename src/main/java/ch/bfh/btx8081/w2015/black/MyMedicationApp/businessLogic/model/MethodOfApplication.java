package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MethodOfApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int methodOfApplicationId = 0;
	@OneToMany(mappedBy="MethodOfApplication")
	private ArrayList<Prescription> perscriptions = null;
	public MethodOfApplication(){
		
	}
}

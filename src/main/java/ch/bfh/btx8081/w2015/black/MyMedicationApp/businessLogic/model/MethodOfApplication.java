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
	@OneToMany(mappedBy="methodOfApplication")
	private ArrayList<Prescription> prescriptions = null;
	private String name = null;
	public MethodOfApplication(){
		
	}
	public int getMethodOfApplicationId() {
		return methodOfApplicationId;
	}
	public void setMethodOfApplicationId(int methodOfApplicationId) {
		this.methodOfApplicationId = methodOfApplicationId;
	}
	public ArrayList<Prescription> getPerscriptions() {
		return prescriptions;
	}
	public void setPerscriptions(ArrayList<Prescription> perscriptions) {
		this.prescriptions = perscriptions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

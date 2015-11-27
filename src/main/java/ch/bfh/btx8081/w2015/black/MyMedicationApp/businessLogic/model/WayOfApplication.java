package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class WayOfApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int wayOfApplicationId = 0;
	@OneToMany(mappedBy="WayOfApplication")
	private ArrayList<Prescription> prescriptions = null;
	private String name = null;
	public WayOfApplication(){
		
	}
	public int getWayOfApplicationId() {
		return wayOfApplicationId;
	}
	public void setWayOfApplicationId(int wayOfApplicationId) {
		this.wayOfApplicationId = wayOfApplicationId;
	}
	public ArrayList<Prescription> getPrescriptions() {
		return prescriptions;
	}
	public void setPrescriptions(ArrayList<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;

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
	@OneToMany(mappedBy="wayOfApplication")
	private List<Prescription> prescriptions = null;
	private String name = null;
	public WayOfApplication(){
		
	}
	public int getWayOfApplicationId() {
		return wayOfApplicationId;
	}
	public void setWayOfApplicationId(int wayOfApplicationId) {
		this.wayOfApplicationId = wayOfApplicationId;
	}
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}
	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *For more information look at the Classdiagram 
 * @author Marwin
 */
@Entity
public class WayOfApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int wayOfApplicationId = 0;
	@OneToMany(mappedBy="wayOfApplication")
	private List<Prescription> prescriptions = null;
	private String name = "";
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + wayOfApplicationId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WayOfApplication other = (WayOfApplication) obj;
		if (wayOfApplicationId != other.wayOfApplicationId)
			return false;
		return true;
	}
}

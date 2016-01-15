package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;

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
	private List<Prescription> prescriptions = null;
	private String name = "";
	public MethodOfApplication(){
		
	}
	public int getMethodOfApplicationId() {
		return methodOfApplicationId;
	}
	public void setMethodOfApplicationId(int methodOfApplicationId) {
		this.methodOfApplicationId = methodOfApplicationId;
	}
	public List<Prescription> getPerscriptions() {
		return prescriptions;
	}
	public void setPerscriptions(List<Prescription> perscriptions) {
		this.prescriptions = perscriptions;
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
		result = prime * result + methodOfApplicationId;
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
		MethodOfApplication other = (MethodOfApplication) obj;
		if (methodOfApplicationId != other.methodOfApplicationId)
			return false;
		return true;
	}
}

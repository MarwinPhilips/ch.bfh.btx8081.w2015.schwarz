package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

//import java.awt.Image;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Medicament {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int medicamentId = 0;
	private String name = "";
	private String substance = "";
	private String quantityUnit = "";
	private String glnNumber = "";
	//private Image picture = null; Acutally no Image displayed
	private String packageInsert = "";
	
	public Medicament(){
		
	}
	public int getId() {
		return medicamentId;
	}
	public void setId(int id) {
		this.medicamentId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubstance() {
		return substance;
	}
	public void setSubstance(String substance) {
		this.substance = substance;
	}
	public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	public String getGlnNumber() {
		return glnNumber;
	}
	public void setGlnNumber(String glnNumber) {
		this.glnNumber = glnNumber;
	}
//	public Image getPicture() {
//		return picture;
//	}
//	public void setPicture(Image picture) {
//		this.picture = picture;
//	}
	public String getPackageInsert() {
		return packageInsert;
	}
	public void setPackageInsert(String packageInsert) {
		this.packageInsert = packageInsert;
	}
}
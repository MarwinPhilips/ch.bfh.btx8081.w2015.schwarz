package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import javax.persistence.Entity;

@Entity
public class MedicationOverviewView {
	private Person person;
	private Prescription prescription;
	private String name;
	private String dose;
	private String scheme;
	private String note;
}

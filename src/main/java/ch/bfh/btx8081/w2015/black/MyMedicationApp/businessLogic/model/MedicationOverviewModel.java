package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;
import java.util.Observable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MssqlEntityManager;

public class MedicationOverviewModel extends Observable {
	List<MedicationOverviewView> medications = null;
	EntityManager em = null;
	public MedicationOverviewModel(){
		em = MssqlEntityManager.createEntityManager();
	}
	public void loadData(){
		loadMedications();
		setChanged();
		notifyObservers(null);
	}
	private void loadMedications(){
		EntityTransaction t = em.getTransaction();
		t.begin();
		TypedQuery<MedicationOverviewView> q = em.createQuery("from MedicationOverviewView m where m.person.personId = :id", MedicationOverviewView.class);
		q.setParameter("id", 1); // ToDo: Replace ID with currently logged in PersonId
		medications = q.getResultList();
		t.commit();
	}
	public List<MedicationOverviewView> getMedications() {
		return medications;
	}
	public void setMedications(List<MedicationOverviewView> medications) {
		this.medications = medications;
	}
}

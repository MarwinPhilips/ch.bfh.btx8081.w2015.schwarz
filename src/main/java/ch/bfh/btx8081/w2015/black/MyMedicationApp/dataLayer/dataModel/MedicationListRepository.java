package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationList;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IMedicationListRepository;

public class MedicationListRepository extends MssqlRepository implements IMedicationListRepository {
	public MedicationListRepository(){
		
	}
	public List<MedicationList> loadMedications(int personId){
		EntityTransaction t = em.getTransaction();
		t.begin();
		TypedQuery<MedicationList> q = em.createQuery("from MedicationList m where m.person.personId = :id", MedicationList.class);
		q.setParameter("id", personId);
		List<MedicationList> medications = q.getResultList();
		t.commit();
		return medications;
	}
}

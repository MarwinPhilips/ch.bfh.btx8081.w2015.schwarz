package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import java.util.List;

import javax.persistence.TypedQuery;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationList;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IMedicationListRepository;
/**
 * All MedicationList POJO need to be loaded over this Repository. Since MedicationList is based
 * on a view, you can not persist any MedicationList. Use the PrescriptionRepository therefore.
 * @author Marwin
 */
public class MedicationListRepository extends MssqlRepository implements IMedicationListRepository {
	public MedicationListRepository(){
		super();
	}
	/**
	 * Loads all MecicationList for the Person with @personId. 
	 * There are some filter criteria on the View.
	 */
	public List<MedicationList> loadMedications(int personId){
		beginTransaction();
		TypedQuery<MedicationList> q = em.createQuery("from MedicationList m where m.person.personId = :id", MedicationList.class);
		q.setParameter("id", personId);
		List<MedicationList> medications = q.getResultList();
		commitTransaction();
		return medications;
	}
}

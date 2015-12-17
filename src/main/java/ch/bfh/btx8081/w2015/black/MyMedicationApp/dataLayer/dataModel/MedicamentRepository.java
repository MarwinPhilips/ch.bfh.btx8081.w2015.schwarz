package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import java.util.List;

import javax.persistence.TypedQuery;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Medicament;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IMedicamentRepository;

public class MedicamentRepository extends MssqlRepository implements IMedicamentRepository {
	
	public MedicamentRepository() {
		super();
	}
	@Override
	public List<Medicament> getAllMedicaments() {
		beginTransaction();
		TypedQuery<Medicament> q = em.createQuery("select m from Medicament m", Medicament.class);
		List<Medicament> medicaments = q.getResultList();
		commitTransaction();
		return medicaments;
	}


}

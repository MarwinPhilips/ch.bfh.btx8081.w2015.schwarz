package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import java.util.List;

import javax.persistence.TypedQuery;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MethodOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.WayOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IMethodOfApplicationRepository;

public class MethodOfApplicationRepository extends MssqlRepository implements
		IMethodOfApplicationRepository {

	@Override
	public List<MethodOfApplication> getAllMethodOfApplication() {
		beginTransaction();
		TypedQuery<MethodOfApplication> q = em.createQuery("select m from MethodOfApplication m", MethodOfApplication.class);
		List<MethodOfApplication> wayOfApplications = q.getResultList();
		commitTransaction();
		return wayOfApplications;
	}

}
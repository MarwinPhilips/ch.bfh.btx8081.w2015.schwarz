package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import java.util.List;

import javax.persistence.TypedQuery;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.WayOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.IWayOfApplicationRepository;

public class WayOfApplicationRepository extends MssqlRepository implements IWayOfApplicationRepository {
	/**
	 * returns all Ways of Application without any filter.
	 */
	@Override
	public List<WayOfApplication> getAllWayOfApplication() {
		beginTransaction();
		TypedQuery<WayOfApplication> q = em.createQuery("select w from WayOfApplication w", WayOfApplication.class);
		List<WayOfApplication> wayOfApplications = q.getResultList();
		commitTransaction();
		return wayOfApplications;
	}
}
package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import java.util.List;

import javax.persistence.TypedQuery;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.ITimeSchemeRepository;

public class TimeSchemeRepository extends MssqlRepository implements ITimeSchemeRepository {

	public TimeSchemeRepository() {
		super();
	}
	@Override
	public List<TimeScheme> getAllTimeschemes() {
		beginTransaction();
		TypedQuery<TimeScheme> q = em.createQuery("select t from TimeScheme t", TimeScheme.class);
		List<TimeScheme> timeSchemes = q.getResultList();
		commitTransaction();
		return timeSchemes;
	}
	public void saveTimeScheme(TimeScheme t){
		transaction = em.getTransaction();
		transaction.begin();
		
	}
	/**
	 * Returns a persisted TimeScheme with no attributes set except the ID.
	 */
	@Override
	public TimeScheme getNewTimeScheme() {
		beginTransaction();
		TimeScheme ts = new TimeScheme();
		em.persist(ts);
		commitTransaction();
		return ts;
	}
	@Override
	public TimeScheme persist(TimeScheme timeScheme) {
		beginTransaction();
		em.persist(timeScheme);
		commitTransaction();
		return timeScheme;
	}
}

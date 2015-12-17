package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import java.util.List;

import javax.persistence.TypedQuery;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.ITimeSchemeRepository;
/**
 * All TimeScheme POJO need to be created, loaded and persisted over this Repository.
 * @author Marwin
 *
 */
public class TimeSchemeRepository extends MssqlRepository implements ITimeSchemeRepository {

	public TimeSchemeRepository() {
		super();
	}
	/**
	 * returns all TimeSchemes. Since they can not be deleted yet no filter need to be applied.
	 */
	@Override
	public List<TimeScheme> getAllTimeschemes() {
		beginTransaction();
		TypedQuery<TimeScheme> q = em.createQuery("select t from TimeScheme t", TimeScheme.class);
		List<TimeScheme> timeSchemes = q.getResultList();
		commitTransaction();
		return timeSchemes;
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
}

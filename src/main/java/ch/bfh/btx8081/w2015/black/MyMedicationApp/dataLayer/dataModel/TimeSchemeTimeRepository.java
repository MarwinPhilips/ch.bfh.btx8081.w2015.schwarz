package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeSchemeTime;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.ITimeSchemeTimeRepository;
/**
 * All TimeSchemeTime POJO need to be created, loaded and persisted over this Repository.
 * @author Marwin
 *
 */
public class TimeSchemeTimeRepository extends MssqlRepository implements ITimeSchemeTimeRepository {
	public TimeSchemeTimeRepository(){
		super();
	}
	/**
	 * Returns a persisted TimeSchemeTime with no attributes set except the ID.
	 */
	@Override
	public TimeSchemeTime getNewTimeSchemeTime() {
		beginTransaction();
		TimeSchemeTime tst = new TimeSchemeTime();
		em.persist(tst);
		commitTransaction();
		return tst;
	}
}

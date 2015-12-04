package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeSchemeTime;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces.ITimeSchemeTimeRepository;

public class TimeSchemeTimeRepository extends MssqlRepository implements ITimeSchemeTimeRepository {
	public TimeSchemeTimeRepository(){
		
	}

	@Override
	public TimeSchemeTime getNewTimeSchemeTime() {
		beginTransaction();
		TimeSchemeTime tst = new TimeSchemeTime();
		em.persist(tst);
		commitTransaction();
		return tst;
	}

	@Override
	public TimeSchemeTime persist(TimeSchemeTime timeSchemeTime) {
		beginTransaction();
		em.persist(timeSchemeTime);
		commitTransaction();
		return timeSchemeTime;
	}
}

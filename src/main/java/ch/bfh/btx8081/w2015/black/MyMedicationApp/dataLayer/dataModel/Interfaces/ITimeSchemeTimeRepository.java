package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeSchemeTime;

public interface ITimeSchemeTimeRepository {
	public TimeSchemeTime getNewTimeSchemeTime();
	public TimeSchemeTime persist(TimeSchemeTime timeSchemeTime);
}

package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeSchemeTime;
/**
 *For more information look at the Classdiagram 
 * @author Marwin
 */
public interface ITimeSchemeTimeRepository extends IRepository{
	public TimeSchemeTime getNewTimeSchemeTime();
}

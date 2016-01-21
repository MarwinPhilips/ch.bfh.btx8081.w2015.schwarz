package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces;

import java.util.List;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.WayOfApplication;
/**
 *For more information look at the Classdiagram 
 * @author Marwin
 */
public interface IWayOfApplicationRepository extends IRepository {
	public List<WayOfApplication> getAllWayOfApplication();
}

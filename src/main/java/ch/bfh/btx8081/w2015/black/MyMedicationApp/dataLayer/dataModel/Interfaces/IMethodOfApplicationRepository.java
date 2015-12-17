package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces;

import java.util.List;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MethodOfApplication;

public interface IMethodOfApplicationRepository extends IRepository {
	public List<MethodOfApplication> getAllMethodOfApplication();
}

package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.DosisScheme;

public interface IDosisSchemeRepository extends IRepository {
	public DosisScheme getNewDosisScheme();
}

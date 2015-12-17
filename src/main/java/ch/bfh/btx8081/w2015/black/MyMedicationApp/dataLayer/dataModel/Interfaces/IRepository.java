package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces;

public interface IRepository {
	public Object persist (Object objectToPersist);
	public void remove(Object objectToRemove);
}

package ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.Interfaces;


public interface IRepository {
	public Object persist (Object objectToPersist);
	public void remove(Object objectToRemove);
	public Object getById(Class c, int id);
	public Object merge(Object objectToMerge);
}

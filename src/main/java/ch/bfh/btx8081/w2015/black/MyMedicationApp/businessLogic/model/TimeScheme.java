package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 *For more information look at the Classdiagram 
 * @author Marwin
 */
@Entity
public class TimeScheme{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int timeSchemeId = 0;
	@OneToMany(mappedBy="timeScheme")
	private List<TimeSchemeTime> timeSchemeTimes = null;
	@OneToMany(mappedBy="timeScheme")
	private List<Prescription> prescriptions = null;
	private String name = null;
	private String repetitionType = null;
	public TimeScheme(){
	}
	public int getTimeSchemeId() {
		return timeSchemeId;
	}
	public void setTimeSchemeId(int timeSchemeId) {
		this.timeSchemeId = timeSchemeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRepetitionType() {
		return repetitionType;
	}
	public void setRepetitionType(String repetitionType) {
		this.repetitionType = repetitionType;
	}
	public List<TimeSchemeTime> getTimeSchemeTimes() {
		return timeSchemeTimes;
	}
	public void setTimeSchemeTimes(
			List<TimeSchemeTime> timeSchemeTimes) {
		this.timeSchemeTimes = timeSchemeTimes;
	}
	@Override
	public int hashCode() {
		return timeSchemeId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeScheme other = (TimeScheme) obj;
		if (timeSchemeId != other.timeSchemeId)
			return false;
		return true;
	}
	
}

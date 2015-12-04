package ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model;

import java.time.Duration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
@Entity
public class TimeSchemeTime {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int timeSchemeTimespanId;

	/**
	 * Duration can not be persisted. Therefore we persist it as a string with the correlated parsing :(
	 * See: http://stackoverflow.com/questions/28427525/how-to-model-java-time-duration-in-mysql-database
	 */
	@Transient
	private Duration time;
	private String time_duration;
	private TimeScheme timeScheme;
	public TimeSchemeTime(){
	}
	public Duration getTimespan() {
		return time;
	}
	public TimeScheme getTimeScheme() {
		return timeScheme;
	}
	public void setTimeScheme(TimeScheme timeScheme) {
		this.timeScheme = timeScheme;
	}
	public int getTimeSchemeTimespanId() {
		return timeSchemeTimespanId;
	}
	public void setTimeSchemeTimespanId(int timeSchemeTimespanId) {
		this.timeSchemeTimespanId = timeSchemeTimespanId;
	}
	@PostLoad
	public void init() {
	  this.time = this.time_duration == null ? null : Duration.parse(this.time_duration);
	};
	public void setTime(Duration time) {
	  this.time_duration = time == null ? null : time.toString();
	  this.time=time;
	}
	
}

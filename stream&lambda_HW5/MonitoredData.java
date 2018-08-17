package hmwrk5;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class MonitoredData {
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String activity;
		
	public MonitoredData() {
		
	}
	
	public MonitoredData(String startDate,String startTime,String endDate, String endTime, String activity) {
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.activity = activity;		
	}
	
	public int getStartDay() {
		String[] parts = this.startDate.split("-");
		int day = Integer.parseInt(parts[2]);
		return day;
	}
	
	public int getEndDay() {
		String[] parts = this.endDate.split("-");
		int day = Integer.parseInt(parts[2]);
		return day;
	}
	
	
//	public int getStartTimeSeconds() {
//		LocalTime t = LocalTime.parse(this.startTime);
//		
//		return t.toSecondOfDay();
//	}
	
	public LocalDateTime getStartTimeFormatted() {
		DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime t = f.parseLocalDateTime(this.startDate+" "+this.startTime);
		
		return t;
	}
	
	public LocalDateTime getEndTimeFormatted() {
		DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime t = f.parseLocalDateTime(this.endDate+" "+this.endTime);
		
		return t;
	}
	
	public long getDuration () {
		DateTimeFormatter f = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime t = f.parseLocalDateTime(this.startDate+" "+this.startTime);
		LocalDateTime q = f.parseLocalDateTime(this.endDate+" "+this.endTime);
		Seconds seconds = Seconds.secondsBetween(t, q);
		
		return seconds.getSeconds();
		
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getActivity() {
		return activity;
	}


	public void setActivity(String activity) {
		this.activity = activity;
	}
}

package it.polito.tdp.formula1.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Race {
	
	private int raceId ;
	private Year year ;
	private int round ;
	private int circuitId ; // refers to {@link Circuit}
	private String name ;
	private LocalDate date ;
	private LocalTime time ;
	private String url ;
	
	private List<Drivers> partecipanti ;
	
	public Race(int raceId, Year year, int round, int circuitId, String name, LocalDate date, LocalTime time,
			String url) {
		super();
		this.raceId = raceId;
		this.year = year;
		this.round = round;
		this.circuitId = circuitId;
		this.name = name;
		this.date = date;
		this.time = time;
		this.url = url;
		this.addPartecipanti(new ArrayList<Drivers> ()) ;
	}
	public int getRaceId() {
		return raceId;
	}
	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getCircuitId() {
		return circuitId;
	}
	public void setCircuitId(int circuitId) {
		this.circuitId = circuitId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Race [raceId=" + raceId + ", year=" + year + ", round=" + round + ", circuitId=" + circuitId + ", name="
				+ name + ", date=" + date + ", time=" + time + ", url=" + url + "]";
	}

	public List<Drivers> getPartecipanti() {
		return partecipanti;
	}
	public void addPartecipanti(List<Drivers> partecipanti) {
		this.partecipanti = partecipanti;
	}
	
	

}

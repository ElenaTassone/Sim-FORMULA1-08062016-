package it.polito.tdp.formula1.model;


import java.util.*;

import it.polito.tdp.formula1.db.F1DAO;

public class Model {
	
	private F1DAO dao ;
	private List<Season> seasons ;
	private Map <Integer, Circuit> allCircuiti ;
	private Map<Integer, Drivers> allDrivers ;
	
	public Model(){
		this.dao = new F1DAO () ;
		this.allCircuiti = dao.getAllCircuiti()  ;
		this.allDrivers = dao.getAllDrivers ()  ;
	}
	
	
	public List<Season> getAnni() {
		if(seasons == null){
			seasons = dao.getAllSeasons() ;
		}
	
		return seasons;
	}


	public List<Circuit> getCircuiti(Season s ) {
		List<Integer> id = dao.getCircuitiAnno (s.getYear()) ;
		List<Circuit> list = new ArrayList<Circuit> () ;
		for(Integer i : id){
			list.add(allCircuiti.get(i)) ;
		}
		Collections.sort(list);
		
		return list;
	}


	public Race getInfo(Circuit c, Season s) {
		Race info = dao.getInfo(c.getCircuitId(), s.getYear().toString()) ;
		List<Integer> inte = dao.getPartecipanti(info.getRaceId());
		List<Drivers> drivers = new ArrayList<Drivers> () ;
		for(Integer id : inte){
			drivers.add(allDrivers.get(id));
		}
		Collections.sort(drivers);
		info.addPartecipanti(drivers);
		
		
		return info;
	}


}

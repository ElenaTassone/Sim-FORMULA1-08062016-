package it.polito.tdp.formula1.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.formula1.model.Circuit;
import it.polito.tdp.formula1.model.Drivers;
import it.polito.tdp.formula1.model.Race;
import it.polito.tdp.formula1.model.Season;


public class F1DAO {

	public List<Season> getAllSeasons() {
		
		String sql = "SELECT year, url FROM seasons ORDER BY year" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Season> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(new Season(Year.of(rs.getInt("year")), rs.getString("url"))) ;
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public static void main(String[] args) {
		F1DAO dao = new F1DAO() ;
		
//		List<Season> seasons = dao.getAllSeasons() ;
//		System.out.println(seasons);
//		System.out.println(dao.getAllCircuiti());
//		System.out.println(dao.getCircuitiAnno(Year.of(2000)));
//		System.out.println(dao.getInfo(23, "1999"));
		
		System.out.println(dao.getAllDrivers());

	}

	public Map<Integer, Circuit> getAllCircuiti() {

		String sql = "SELECT * FROM circuits " ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			Map<Integer, Circuit> map = new TreeMap<Integer, Circuit>() ;
			while(rs.next()) {
				map.put(rs.getInt("circuitId"), new Circuit(rs.getInt("circuitId"), rs.getString("circuitRef"), rs.getString("name"), 
						rs.getString("location"), rs.getString("country"), rs.getDouble("lat"),
						rs.getDouble("lng"), rs.getInt("alt"), rs.getString("url"))) ;
			}
			
			conn.close();
			return map ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
}

	public List<Integer> getCircuitiAnno(Year year) {
		String sql = "SELECT c.circuitId FROM circuits as c, races as r WHERE r.circuitId=c.circuitId AND r.year=? " ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setString(1, year.toString());
			
			ResultSet rs = st.executeQuery() ;
			
			List<Integer> id = new ArrayList<Integer> () ;
			while(rs.next()) {
				id.add(rs.getInt("circuitId")) ;
			}
				
			
			conn.close();
			return id ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
	}

	public Race getInfo(int circuitId, String string) {
		
		String sql = "SELECT * FROM races WHERE circuitId=? and year=?" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, circuitId);
			st.setString(2, string);
			
			ResultSet rs = st.executeQuery() ;
			
			Race r =null ;
			while(rs.next()) {
				r = new Race (rs.getInt("raceId"), Year.of(rs.getInt("year")), rs.getInt("round"),
						rs.getInt("circuitId"), rs.getString("name"), rs.getDate("date").toLocalDate(),
						null,rs.getString("url")) ;
			}
			
			conn.close();
			return r;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
	}

	public List<Integer> getPartecipanti(int raceId) {
		
		String sql = "SELECT d.driverId FROM drivers as d, results as res WHERE res.driverId=d.driverId AND res.raceId=? " ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, raceId);
			
			ResultSet rs = st.executeQuery() ;
			
			List<Integer> list = new ArrayList<Integer> () ;
			while(rs.next()) {
				list.add(rs.getInt("driverId")) ;
			}
				
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		}

	public Map<Integer, Drivers> getAllDrivers() {
		
		String sql = "SELECT * FROM drivers " ;
		
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			Map<Integer, Drivers> map = new TreeMap<Integer, Drivers>() ;
			while(rs.next()) {
			LocalDate dob = null; 	
				if(rs.getDate("dob") != null)
					dob = rs.getDate("dob").toLocalDate();
					
			Drivers t = 	new Drivers(rs.getInt("driverId"), rs.getString("driverRef"),
						rs.getInt("number"), rs.getString("code"), rs.getString("forename"), rs.getString("surname"),
						dob, rs.getString("nationality"), rs.getString("url")) ;
			System.out.println(t);
				map.put(t.getDriverId(), t) ;
			}
			
			conn.close();
			return map ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

	}
	
}

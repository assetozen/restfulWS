package com.assegd.RESTfulUsingJersey;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

public class AlienRepository {
	java.sql.Connection conn = null;

	List<Alien> aliens;

	public AlienRepository() {
		String url = "jdbc:mysql://localhost:3306/restdb?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // first i used it without ".cj" but the console exception forces me to add it and now it is working properly
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * gets all the aliens data from the database 
	 * @return
	 */
	public List<Alien> getAliens() {
		List<Alien> aliens = new ArrayList<>();
		String sql = "select * from alien";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));

				aliens.add(a);

			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return aliens;
	}

	/**
	 * gets an object with similar id from the db
	 * 
	 * @param id
	 * @return
	 */
	public Alien getAlien(int id) {
		Alien a = new Alien();
		String sql = "select * from alien where id=" + id;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return a;
	}

	/**
	 * Creates a new object and adds it to DB
	 * @param a1
	 */
	public void create(Alien a1) {
		String sql = "insert into alien values(?,?,?)";

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getName());
			st.setInt(3, a1.getPoints());
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Updates a record with the given data(use the PUT method for RESTful)
	 * @param a1
	 */
	public void update(Alien a1) {
		String sql = "update alien SET name=?, points=? WHERE id=?";

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, a1.getName());
			st.setInt(2, a1.getPoints());
			st.setInt(3, a1.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Deletes a data, 
	 * @param a1
	 */
	public void delete(int id) {
		
		String sql = "delete from alien where id=?";

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}

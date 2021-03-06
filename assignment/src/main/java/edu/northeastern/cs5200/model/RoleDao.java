package edu.northeastern.cs5200.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleDao {
	DbConfig cfg;
	public static RoleDao instance = null;
	public static RoleDao getInstance() {
		if (instance == null) {
			instance = new RoleDao();
		}
		return instance;
	}
	
	private RoleDao() {
		cfg = new DbConfig();
	}
	
	public int assignWebsiteRole(int developerId, int websiteId, Role role) throws SQLException, ClassNotFoundException {
		String sql = "INSERT Into WebsiteRole (websiteID, role, developerID) VALUES (?,?,?)";
		Connection conn = cfg.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, websiteId);
		statement.setString(2, role.toString());
		statement.setInt(3, developerId);
		int res = statement.executeUpdate();
		statement.close();
		conn.close();
		return res;
	}
	
	public int assignPageRole(int developerId, int pageId, Role role) throws SQLException, ClassNotFoundException {
		String sql = "INSERT Into PageRole (pageID, developerID, role) VALUES (?,?,?)";
		Connection conn = cfg.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, pageId);
		statement.setInt(2, developerId);
		statement.setString(3, role.toString());
		int res = statement.executeUpdate();
		statement.close();
		conn.close();
		return res;
	}
	
	public int deleteWebsiteRole(int developerId, int websiteId, Role role) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM WebsiteRole WHERE developerID=? AND websiteID=? AND role=?";
		Connection conn = cfg.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, developerId);
		statement.setInt(2, websiteId);
		statement.setString(3, role.toString());
		int res = statement.executeUpdate();
		statement.close();
		conn.close();
		return res;
	}
	
	public int deletePageRole(int developerId, int pageId, Role role) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM PageRole WHERE developerID=? AND pageID=? AND role=?";
		Connection conn = cfg.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, developerId);
		statement.setInt(2, pageId);
		statement.setString(3, role.toString());
		int res = statement.executeUpdate();
		statement.close();
		conn.close();
		return res;
	}	
	
	public int swapPageRoles(Developer dev1, Developer dev2, Page page) throws ClassNotFoundException, SQLException {
		int res = 0;
		String sql = "UPDATE PageRole a INNER JOIN PageRole b on a.developerID <> b.developerID SET a.role = b.role WHERE a.developerID in (?,?) AND b.developerID in (?,?) AND a.pageID = ? AND b.pageID= ?";
		Connection conn = cfg.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, dev1.getId());
		statement.setInt(2, dev2.getId());
		statement.setInt(3, dev1.getId());
		statement.setInt(4, dev2.getId());
		statement.setInt(5, page.getId());
		statement.setInt(6, page.getId());
		res = statement.executeUpdate();
		statement.close();
		conn.close();
		return res;
	}
}

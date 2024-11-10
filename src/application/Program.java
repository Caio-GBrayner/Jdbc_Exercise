package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		
		Connection conn = null;
		Statement st = null;

		ResultSet rs = null;
		try {
			conn = DB.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery("select * from department");

			while (rs.next()) {
				System.out.println(rs.getInt("Id") + "," + rs.getString("Name"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			
			ps = conn.prepareStatement(
					"INSERT INTO seller "
					+"(NAME, Email, BirthDate, BaseSalary, DepartmentId)"
					+"VALUES"
					+"(?, ?, ?, ?, ?)");
			
			ps.setString(1, "Jeffson Macedo");
			ps.setString(2, "Jeff@outlook.com");
			ps.setDate(3, new java.sql.Date(sdf.parse("13/10/2001").getTime()));
			ps.setDouble(4, 1600.0);
			ps.setInt(5, 2);
			
			int rowsAffected = ps.executeUpdate();
			
			System.out.println("Done! RowsAffected: " +rowsAffected);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatment(st);
			DB.closeConnection();
			DB.closeStatment(ps);
		}
	}

}

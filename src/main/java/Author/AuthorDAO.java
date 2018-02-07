package Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDAO {
	
	@Autowired()
	private DataSource dataSource;
	
	
	public void add(Author a) {
		try {
			Connection conn=dataSource.getConnection();
			Statement stmt=conn.createStatement();
			

			stmt = conn.createStatement();
			String sql;
			sql = "INSERT INTO author(id,name)"+"VALUES('"+a.getId()+"','"+a.getName()+"')";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();

		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Author> load(){
		ArrayList<Author> Authors=new ArrayList<Author>();
		try {
			Connection conn=dataSource.getConnection();
			Statement stmt=conn.createStatement();
		String sql;
		sql = "SELECT * FROM Author";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			// Retrieve by column name
			int i = rs.getInt("id");
			String name=rs.getString("name");
			Authors.add(new Author(i,name));
		}
		

		// STEP 6: Clean-up environment
		rs.close();
		stmt.close();
		conn.close();
		return Authors;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
		
	}
	
	
	public void delete(int id) {
		try {
			
			Connection conn=dataSource.getConnection();
			Statement stmt=conn.createStatement();
			
			String Query="Delete from Author where id="+id;
			stmt.executeUpdate(Query);
			stmt.close();
			conn.close();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}

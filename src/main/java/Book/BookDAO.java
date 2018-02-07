package Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BookDAO {

	@Autowired
	private DataSource dataSource;
	
	public void add(Book b) {
		try {
			Connection conn=dataSource.getConnection();
			Statement stmt=conn.createStatement();
			

			stmt = conn.createStatement();
			String sql;
			sql = "INSERT INTO Book(id,name,price)"+"VALUES('"+b.getId()+"','"+b.getName()+
					"','"+b.getPrice()+"')";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();

		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Book> load(){
		ArrayList<Book> Books=new ArrayList<Book>();
		try {
			Connection conn=dataSource.getConnection();
			Statement stmt=conn.createStatement();
		String sql;
		sql = "SELECT * FROM book";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			// Retrieve by column name
			int i = rs.getInt("id");
			String name=rs.getString("name");
			int price=rs.getInt("price");
			Books.add(new Book(i,name,price));
		}
		

		// STEP 6: Clean-up environment
		rs.close();
		stmt.close();
		conn.close();
		return Books;
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
			
			String Query="Delete from book where id="+id;
			stmt.executeUpdate(Query);
			stmt.close();
			conn.close();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

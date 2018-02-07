package AuthorBook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Author.Author;
import Book.Book;

@Repository
public class AuthorBookDAO {

	@Autowired()
	private DataSource dataSource;

	public void add(AuthorBook a) {
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();

			stmt = conn.createStatement();
			String sql;
			sql = "INSERT INTO authorbook(id,name)" + "VALUES('" + a.getId() + "','" + a.getBook_id() + "','"
					+ a.getAuthor_id() + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<AuthorBook> load() {
		ArrayList<AuthorBook> AuthorBook = new ArrayList<AuthorBook>();
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM AuthorBook";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				int i = rs.getInt("id");
				int book_id = rs.getInt("book_id");
				int author_id = rs.getInt("author_id");
				AuthorBook.add(new AuthorBook(i, book_id, author_id));
			}

			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
			return AuthorBook;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Author> findwithbook(int book) {
		ArrayList<Author> Authors = new ArrayList<Author>();
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM AuthorBook inner join author on AuthorBook.Author_id=Author.id where Book_id=" + book;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				int i = rs.getInt("id");
				String name = rs.getString("name");
				Authors.add(new Author(i, name));
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

	public ArrayList<Book> findwithauthor(int author) {
		ArrayList<Book> Books = new ArrayList<Book>();
		try {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM AuthorBook inner join book on AuthorBook.Book_id=book.id where author_id=" + author;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				int i = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				Books.add(new Book(i, name, price));
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

			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();

			String Query = "Delete from AuthorBook where id=" + id;
			stmt.executeUpdate(Query);
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

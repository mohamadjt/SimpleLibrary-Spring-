package AuthorBook;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Author.Author;
import Book.Book;

@Service
public class AuthorBookMGR {
	
	@Autowired
	AuthorBookDAO AuthorBookDAO;
	
	public void add(AuthorBook a) {
		AuthorBookDAO.add(a);
	}
	public void delete(int id) {
		AuthorBookDAO.delete(id);
	}
	public ArrayList<Book> findbyauthor(int author){
		return AuthorBookDAO.findwithauthor(author);
	}
	public ArrayList<Author> findbybook(int book){
		return AuthorBookDAO.findwithbook(book);
	}

}

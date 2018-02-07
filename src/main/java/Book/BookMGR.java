package Book;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookMGR {

	
	@Autowired
	BookDAO BookDAO;
	
	public void add(Book b) {
		BookDAO.add(b);
	}
	public void delete(int id) {
		BookDAO.delete(id);
	}
	public ArrayList<Book> load(){
		return BookDAO.load();
	}
}

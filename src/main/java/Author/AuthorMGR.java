package Author;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorMGR {
	
	@Autowired
	AuthorDAO AuthorDAO;
	
	
	public void add(Author a) {
		AuthorDAO.add(a);
	}
	public void delete(int id) {
		AuthorDAO.delete(id);
	}
	public ArrayList<Author> load() {
		return AuthorDAO.load();
	}
	

}

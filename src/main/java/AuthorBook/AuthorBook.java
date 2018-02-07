package AuthorBook;

public class AuthorBook {
	private int id;
	private int Book_id;
	private int Author_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBook_id() {
		return Book_id;
	}
	public void setBook_id(int book_id) {
		Book_id = book_id;
	}
	public int getAuthor_id() {
		return Author_id;
	}
	public void setAuthor_id(int author_id) {
		Author_id = author_id;
	}
	
	public AuthorBook() {
		
	}
	public AuthorBook(int id,int book_id,int author_id) {
		this.id=id;
		this.Book_id=book_id;
		this.Author_id=author_id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " book with id = "+Book_id+" belongs to author with id = "+Author_id;
		}

}

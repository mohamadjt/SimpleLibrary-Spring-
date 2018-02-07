import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Author.Author;
import Author.AuthorMGR;
import Book.Book;
import Book.BookMGR;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		Book b=new Book(2,"shahname",1000);
		BookMGR bm=ctx.getBean(BookMGR.class);
		bm.add(b);
		
	}

}

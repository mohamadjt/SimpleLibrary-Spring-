
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Author.Author;
import Author.AuthorDAO;
import Author.AuthorMGR;
import AuthorBook.AuthorBook;
import AuthorBook.AuthorBookMGR;
import Book.Book;
import Book.BookMGR;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

public class Library extends JFrame {

	private JPanel contentPane;
	private JTable tableAuthor;
	private JTable tableBook;
	private JTable tableAuthorBook;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JLabel lblId;
	private JLabel lblName;
	private JLabel lblPrice;
	private String s = "";
	private String x = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library frame = new Library();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void init() {
		tableAuthor = new JTable();
		tableAuthor.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		tableAuthor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAuthor.setColumnSelectionAllowed(true);
		tableAuthor.setCellSelectionEnabled(true);

		tableAuthor.setBounds(115, 38, 149, 273);
		contentPane.add(tableAuthor);

		tableBook = new JTable();
		tableBook.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		tableBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableBook.setColumnSelectionAllowed(true);
		tableBook.setCellSelectionEnabled(true);

		tableBook.setBounds(301, 35, 204, 276);
		contentPane.add(tableBook);

		tableAuthorBook = new JTable();
		tableAuthorBook.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		tableAuthorBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAuthorBook.setColumnSelectionAllowed(true);
		tableAuthorBook.setCellSelectionEnabled(true);

		tableAuthorBook.setBounds(526, 34, 197, 277);
		contentPane.add(tableAuthorBook);

		textFieldID = new JTextField();
		textFieldID.setBounds(115, 359, 64, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);

		lblId = new JLabel("ID ");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(125, 334, 46, 14);
		contentPane.add(lblId);

		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(222, 334, 96, 14);
		contentPane.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setBounds(199, 359, 86, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		lblPrice = new JLabel("Price  ");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrice.setBounds(323, 334, 96, 14);
		contentPane.add(lblPrice);

		textFieldPrice = new JTextField();
		textFieldPrice.setBounds(301, 359, 86, 20);
		contentPane.add(textFieldPrice);
		textFieldPrice.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdd.setBounds(10, 320, 64, 23);
		contentPane.add(btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setBounds(10, 358, 74, 23);
		contentPane.add(btnDelete);

		textFieldPrice.setVisible(false);
		textFieldID.setVisible(false);
		textFieldName.setVisible(false);
		lblId.setVisible(false);
		lblName.setVisible(false);
		lblPrice.setVisible(false);
		authorFill(tableAuthor);
		BookFill(tableBook);

		tableAuthor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				x = "Athur";
				s = "Author";
				lblId.setVisible(true);
				textFieldID.setVisible(true);
				textFieldName.setVisible(true);
				lblName.setVisible(true);
				lblName.setText("Name");
				lblPrice.setVisible(false);
				textFieldPrice.setVisible(false);
				textFieldID.setText(tableAuthor.getValueAt(tableAuthor.getSelectedRow(), 0).toString());
				textFieldName.setText(tableAuthor.getValueAt(tableAuthor.getSelectedRow(), 1).toString());

				fillAuthorBookbyAuthor(tableAuthorBook,
						Integer.parseInt(tableAuthor.getValueAt(tableAuthor.getSelectedRow(), 0).toString()));

			}
		});

		tableBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				x = "Book";
				s = "Book";
				lblId.setVisible(true);
				textFieldID.setVisible(true);
				textFieldName.setVisible(true);
				lblName.setVisible(true);
				lblPrice.setVisible(true);
				lblPrice.setText("Price");
				lblName.setText("Name");
				textFieldPrice.setVisible(true);
				textFieldID.setText(tableBook.getValueAt(tableBook.getSelectedRow(), 0).toString());
				textFieldName.setText(tableBook.getValueAt(tableBook.getSelectedRow(), 1).toString());
				textFieldPrice.setText(tableBook.getValueAt(tableBook.getSelectedRow(), 2).toString());

				fillAuthorBookbyBook(tableAuthorBook,
						Integer.parseInt(tableBook.getValueAt(tableBook.getSelectedRow(), 0).toString()));

			}
		});

		tableAuthorBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				s = "AuthorBook";
				lblId.setVisible(false);
				textFieldID.setVisible(false);
				textFieldName.setVisible(false);
				lblName.setVisible(false);
				lblPrice.setVisible(false);
				textFieldPrice.setVisible(false);
			}
		});

		ActionListener acAdd = new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (s.equals("Author")) {
					ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
					AuthorMGR am = ctx.getBean(AuthorMGR.class);
					am.add(new Author(Integer.parseInt(textFieldID.getText()), textFieldName.getText()));
					JOptionPane.showMessageDialog(null, "Author Successfully Added");
					authorFill(tableAuthor);

				}
				if (s.equals("Book")) {
					ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
					BookMGR bm = ctx.getBean(BookMGR.class);
					bm.add(new Book(Integer.parseInt(textFieldID.getText()), textFieldName.getText(),
							Integer.parseInt(textFieldPrice.getText())));
					JOptionPane.showMessageDialog(null, "Book Successfully Added");
					BookFill(tableBook);

				}
				if (s.equals("AuthorBook")) {
					ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
					AuthorBookMGR abm = ctx.getBean(AuthorBookMGR.class);
					abm.add(new AuthorBook(Integer.parseInt(textFieldID.getText()),
							Integer.parseInt(textFieldName.getText()), Integer.parseInt(textFieldPrice.getText())));
					JOptionPane.showMessageDialog(null, "AuthorBook Successfully Added");

				}

			}
		};

		ActionListener acDelete = new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (s.equals("Author")) {
					ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
					AuthorMGR am = ctx.getBean(AuthorMGR.class);
					am.delete(Integer.parseInt(textFieldID.getText()));
					JOptionPane.showMessageDialog(null, "Author Successfully Deleted");
					authorFill(tableAuthor);

				}
				if (s.equals("Book")) {
					ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
					BookMGR bm = ctx.getBean(BookMGR.class);
					bm.delete(Integer.parseInt(textFieldID.getText()));
					JOptionPane.showMessageDialog(null, "Book Successfully Deleted");
					BookFill(tableBook);

				}
				if (s.equals("AuthorBook")) {
					ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
					AuthorBookMGR abm = ctx.getBean(AuthorBookMGR.class);
					abm.delete(Integer.parseInt(textFieldID.getText()));
					JOptionPane.showMessageDialog(null, "AuthorBook Successfully Deleted");

				}

			}
		};
		btnAdd.addActionListener(acAdd);
		btnDelete.addActionListener(acDelete);

	}

	/**
	 * Create the frame.
	 */
	public Library() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();

	}

	public void authorFill(JTable jb) {
		DefaultTableModel model = new DefaultTableModel(new String[] { "id", "name" }, 0);
		model.addRow(new Object[] { "id", "name" });
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		ArrayList<Author> Authors = new ArrayList<Author>();
		AuthorMGR am = ctx.getBean(AuthorMGR.class);
		Authors.addAll(am.load());

		for (int i = 0; i < Authors.size(); i++) {
			Author a = Authors.get(i);
			model.addRow(new Object[] { a.getId(), a.getName() });

		}
		jb.setModel(model);

	}

	public void BookFill(JTable jb) {
		DefaultTableModel model = new DefaultTableModel(new String[] { "id", "name", "price" }, 0);
		model.addRow(new Object[] { "id", "name", "price" });
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		ArrayList<Book> Books = new ArrayList<Book>();
		BookMGR bm = ctx.getBean(BookMGR.class);
		Books.addAll(bm.load());

		for (int i = 0; i < Books.size(); i++) {
			Book b = Books.get(i);
			model.addRow(new Object[] { b.getId(), b.getName(), b.getPrice() });

		}
		jb.setModel(model);

	}

	public void fillAuthorBookbyAuthor(JTable jb, int id) {
		DefaultTableModel model = new DefaultTableModel(new String[] { "id", "name", "price" }, 0);
		model.addRow(new Object[] { "id", "name", "price" });
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		ArrayList<Book> Books = new ArrayList<Book>();
		AuthorBookMGR abm = ctx.getBean(AuthorBookMGR.class);
		Books.addAll(abm.findbyauthor(id));
		for (int i = 0; i < Books.size(); i++) {
			Book b = Books.get(i);
			model.addRow(new Object[] { b.getId(), b.getName(), b.getPrice() });

		}
		jb.setModel(model);

	}

	public void fillAuthorBookbyBook(JTable jb, int id) {
		DefaultTableModel model = new DefaultTableModel(new String[] { "id", "name" }, 0);
		model.addRow(new Object[] { "id", "name" });
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		ArrayList<Author> Authors = new ArrayList<Author>();
		AuthorBookMGR abm = ctx.getBean(AuthorBookMGR.class);
		Authors.addAll(abm.findbybook(id));
		for (int i = 0; i < Authors.size(); i++) {
			Author a = Authors.get(i);
			model.addRow(new Object[] { a.getId(), a.getName() });

		}
		jb.setModel(model);

	}

}

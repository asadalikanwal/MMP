package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Address;
import business.Author;
import business.Member;
import business.Book;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AddBookController {

	@FXML
	private Label fxAvailabilityError;

	@FXML
	private TextField fxISBN;

	@FXML
	private TextField fxDuration;

	@FXML
	private TextField fxTitle;

	@FXML
	private Label fxAuthorsError;

	@FXML
    private TextField fxAuthorLastName;

    @FXML
    private TextField fxAuthorsFirstName;


	@FXML
	private Label fxDurationError;

	@FXML
	private Label fxISBNError;

	

	@FXML
	private Label fxTitleError;

	@FXML
	private Label fxCopyError;

	@FXML
	private TextField fxCopy;

	
	@FXML
    private Button fxAddBookButton;

	@FXML
	void AddBookClick(ActionEvent event) {

		// Form Validations
		boolean title = Validation.isValid(fxTitle.getText(), "String", fxTitleError);
		boolean iSBN = Validation.isValid(fxISBN.getText(), "String", fxISBNError);
		boolean authorFname = Validation.isValid(fxAuthorsFirstName.getText(), "String", fxAuthorsError);
		boolean authorLname = Validation.isValid(fxAuthorLastName.getText(), "String", fxAuthorsError);
		//boolean availability = Validation.isValid(fxAvailability.getText(), "String", fxAvailabilityError);
		boolean copy = Validation.isValid(fxCopy.getText(), "Number", fxCopyError);
		boolean duration = Validation.isValid(fxDuration.getText(), "Number", fxDurationError);

		if (!title || !iSBN || !authorFname ||!authorLname|| !copy || !duration ) {
			return;
		}

		DataAccessFacade daf = new DataAccessFacade();

		List<Author> book1Author = new ArrayList<Author>();
		book1Author.add(new Author(fxAuthorsFirstName.getText(), fxAuthorLastName.getText(), new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
				"371-455-8765", "World reknown writer. Wrote 100 books", "PhD, 100 Publications"));
		
		Book book = new Book(fxTitle.getText(), fxISBN.getText(), book1Author, true, Integer.parseInt(fxCopy.getText()), Integer.parseInt(fxDuration.getText()) );

		daf.saveNewBook(book);
		System.out.println("Book successfully Added");
		AddBook.INSTANCE.hide();

	}
	
	 @FXML
    void addAuthor(ActionEvent event) throws IOException {
		 System.out.println("addAuthor clicked");
		 AddAuthor.INSTANCE.init(Root.rootStage());
		AddAuthor.INSTANCE.showAndWait();
    }

	public void init() {
//		AddBook.INSTANCE.setMaximized(true);
		AddBook.INSTANCE.setTitle("Add Book");

	}
}
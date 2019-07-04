package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

import business.Address;
import business.Author;
import business.Member;
import business.Book;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AddBookController {
	public List<Author> authors = new ArrayList<>();

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
	private Label fxAuthorsList;

	@FXML
	private Button fxAddBookButton;

	@FXML
	void AddBookClick(ActionEvent event) {

		// Form Validations
		boolean title = Validation.isValid(fxTitle.getText(), "String", fxTitleError);
		boolean iSBN = Validation.isValid(fxISBN.getText(), "String", fxISBNError);
		// boolean availability = Validation.isValid(fxAvailability.getText(), "String",
		// fxAvailabilityError);
		boolean copy = Validation.isValid(fxCopy.getText(), "Number", fxCopyError);
		boolean duration = Validation.isValid(fxDuration.getText(), "Number", fxDurationError);

		if (!title || !iSBN || !copy || !duration) {
			return;
		}

		DataAccessFacade daf = new DataAccessFacade();

		Book book = new Book(fxTitle.getText(), fxISBN.getText(), authors, true, Integer.parseInt(fxCopy.getText()),
				Integer.parseInt(fxDuration.getText()));

		daf.saveNewBook(book);

		Dashboard.INSTANCE.Controller.init();
		AddBook.INSTANCE.hide();

	}

	public void updateAuthorList() {
		String authorListStr = "";
		for (Author a : authors) {
			authorListStr += (a.getFirstName() + " " + a.getLastName() + ", ");
		}
		authorListStr = authorListStr.length() > 0 ? authorListStr.substring(0, authorListStr.length() - 2) : "";
		fxAuthorsList.setText(authorListStr);
		fxAuthorsList.setVisible(true);
	}

	@FXML
	void addAuthor(ActionEvent event) throws IOException {
		AddAuthor addAuthor = new AddAuthor();
		AddAuthor.INSTANCE.init(Root.rootStage());
		AddAuthor.INSTANCE.showAndWait();
	}

	public void init() {
		fxAuthorsList.setVisible(false);
		AddBook.INSTANCE.setTitle("Add Book");
	}

}
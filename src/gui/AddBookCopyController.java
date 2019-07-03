package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Address;
import business.Book;
import business.Member;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class AddBookCopyController {

	@FXML
	private TextField fxBookIsbn;

	@FXML
	private TextField fxCopyNo;

	@FXML
	private Label fxBookIsbnErr;

	@FXML
	private Label fxCopyNoErr;

	@FXML
	private Label fxTitle;

	@FXML
	private Label fxAuthors;

	@FXML
	private Label fxIsbn;

	@FXML
	private Label fxCopy;

	@FXML
	private Label fxMaxCheckout;

	@FXML
	void addBookSearchClick(ActionEvent event) {

		// Form Validations
		boolean bookIsbn = Validation.isValid(fxBookIsbn.getText(), "String", fxBookIsbnErr);

		fxCopyNoErr.setText("");

		if (!bookIsbn) {
			return;
		}

		DataAccessFacade daf = new DataAccessFacade();
		Book book = daf.searchBook(fxBookIsbn.getText());
		if (book == null) {
			fxBookIsbnErr.setText("Book Not Found");
		} else {
			fxBookIsbnErr.setText("");
			fxTitle.setText(book.getTitle());
			fxAuthors.setText(book.getAuthors().toString());
			fxIsbn.setText(book.getIsbn());
			fxMaxCheckout.setText(String.valueOf(book.getMaxCheckoutLength()));
			fxCopy.setText(String.valueOf(book.getTotalNumOfCopy()));
		}
	}

	@FXML
	void addBookCopyClick(ActionEvent event) {

		// Form Validations
		boolean copy = Validation.isValid(fxCopyNo.getText(), "Number", fxCopyNoErr);
		boolean bookIsbn = Validation.isValid(fxIsbn.getText(), "String", fxCopyNoErr);

		if (!copy || !bookIsbn) {
			return;
		}

		DataAccessFacade daf = new DataAccessFacade();
		boolean updateResult = daf.addCopy(fxIsbn.getText(), Integer.parseInt(fxCopyNo.getText()));

		if (!updateResult) {
			fxCopyNoErr.setText("Operation Failed");
		} else {
			fxCopyNoErr.setText("Operation Success");
			fxBookIsbnErr.setText("");
			fxBookIsbn.setText("");

			fxTitle.setText("");
			fxIsbn.setText("");
			fxAuthors.setText("");
			fxMaxCheckout.setText("");
			fxCopy.setText("");
			fxCopyNo.setText("");
		}

	}

	public void init() {
//		AddMember.INSTANCE.setMaximized(true);
		AddMember.INSTANCE.setTitle("Add Book Copy");

	}
}
package gui;

import business.Book;
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

		fxBookIsbnErr.setText("");
		DataAccessFacade daf = new DataAccessFacade();
		Book book = daf.searchBook(fxBookIsbn.getText());
		if (book != null) {
			fxBookIsbnErr.setText("");
			fxTitle.setText(book.getTitle());
			String aut = "";
			for (int i = 0; i < book.getAuthors().size(); i++) {
				aut += book.getAuthors().get(i).getFirstName() + " " + book.getAuthors().get(i).getLastName() + ", ";
			}
			fxAuthors.setText(aut);
			fxIsbn.setText(book.getIsbn());
			fxMaxCheckout.setText(String.valueOf(book.getMaxCheckoutLength()));
			fxCopy.setText(String.valueOf(book.getTotalNumOfCopy()));
		} else {
			fxTitle.setText("");
			fxAuthors.setText("");
			fxIsbn.setText("");
			fxMaxCheckout.setText("");
			fxCopy.setText("");
			fxBookIsbnErr.setText("Book Not Found");
		}
	}

	@FXML
	void addBookCopyClick(ActionEvent event) {

		// Form Validations
		boolean copy = Validation.isValid(fxCopyNo.getText(), "Number", fxCopyNoErr);
		boolean bookIsbn = Validation.isValid(fxIsbn.getText(), "String", fxBookIsbnErr);

		if (!copy || !bookIsbn) {
			fxCopyNoErr.setText("Operation Failed");
			return;
		}
		fxCopyNoErr.setText("");

		DataAccessFacade daf = new DataAccessFacade();
		boolean updateResult = daf.addCopy(fxIsbn.getText(), Integer.parseInt(fxCopyNo.getText()));

		if (updateResult) {
			fxBookIsbnErr.setText("");
			int OldNo = Integer.parseInt(fxCopy.getText());
			int newNo = OldNo + Integer.parseInt(fxCopyNo.getText());
			fxCopy.setText(String.valueOf(newNo));
			fxCopyNo.setText("");
			fxCopyNoErr.setText("Operation Success");
			Dashboard.INSTANCE.Controller.init();
			return;
		} else
			fxCopyNoErr.setText("Operation Failed");

	}

	public void init() {
		AddMember.INSTANCE.setTitle("Add Book Copy");
	}
}

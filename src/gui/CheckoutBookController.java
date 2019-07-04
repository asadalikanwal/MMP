package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Address;
import business.Book;
import business.CheckoutRecord;
import business.Member;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CheckoutBookController {

	Book book;
	Member member;
	DataAccessFacade daf = new DataAccessFacade();

	@FXML
	private TextField fxBookId;

	@FXML
	private TextField fxMemberID;

	@FXML
	private Label fxMemberIDError;

	@FXML
	private Label fxBookIdError;

	@FXML
	private TableView<CheckoutRecord> result;

	@FXML
	private TableColumn tbMemberId;

	@FXML
	private TableColumn tbMemberName;

	@FXML
	private TableColumn tbBookName;

	@FXML
	private TableColumn tbISBN;

	@FXML
	private TableColumn tbBookID;

	@FXML
	private TableColumn tbDate;

	@FXML
	private TableColumn tbStatus;

	@FXML
	void CheckoutBookEvent(ActionEvent event) {
		CheckoutRecord record = new CheckoutRecord(member, book);
		boolean checkout = daf.saveCheckoutRecord(record);
		System.out.println("Checkout Book: " + checkout);

		List<CheckoutRecord> records = daf.searchMember(member.getMemberId());
//		CheckoutRecord[] r = new CheckoutRecord[records.size()];
//		int idx = 0;
//		for (CheckoutRecord r2 : records) {
//			System.out.println(r2);
//			r[idx] = new CheckoutRecord(r2.getDueDate(), r2.isReturnStatus(), r2.getMember().getMemberId(), r2.getMember().getFullName(), r2.getBook().getTitle(), r2.getBook().getIsbn(), r2.getBook().getId());
//
//			System.out.println(r[idx].getMemberID() + " " + r[idx].getMemberName());
//			idx++;
//		}
		result.getItems().setAll(records);
		tbMemberId.setCellValueFactory(new PropertyValueFactory<CheckoutRecord, String>("memberID"));
		tbMemberName.setCellValueFactory(new PropertyValueFactory<CheckoutRecord, String>("memberName"));
		tbBookName.setCellValueFactory(new PropertyValueFactory<CheckoutRecord, String>("bookTitle"));
		tbISBN.setCellValueFactory(new PropertyValueFactory<CheckoutRecord, String>("bookISBN"));
		tbBookID.setCellValueFactory(new PropertyValueFactory<CheckoutRecord, String>("bookId"));
		tbDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecord, String>("dueDate"));
		tbStatus.setCellValueFactory(new PropertyValueFactory<CheckoutRecord, String>("returnStatus"));

	}

	@FXML
	void checkAvailability(ActionEvent event) {
		boolean bookCheck = Validation.isValid(fxBookId.getText(), "String", fxBookIdError);
		if (!bookCheck) {
			return;
		}

		book = daf.searchBook(fxBookId.getText());

		if (book == null) {
			fxBookIdError.setText("Book not found");
			fxBookIdError.setVisible(true);
			System.out.println("Data: " + book);
			return;
		} else {
			System.out.println("Search Book : " + book.toString());
			fxBookIdError.setText("Book found: " + book.getTitle());
			fxBookIdError.setVisible(true);
		}

	}

	@FXML
	void searchMember(ActionEvent event) {
		boolean memberCheck = Validation.isValid(fxMemberID.getText(), "Number", fxMemberIDError);

		if (!memberCheck) {
			return;
		}

		member = daf.srcMember(fxMemberID.getText());

		if (member == null) {
			fxMemberIDError.setText("Invalid Member ID");
			fxMemberIDError.setVisible(true);
			System.out.println("Data: " + member);
			return;
		} else {
			System.out.println("Search Member : " + member.toString());
			fxMemberIDError.setText("Member found: " + member.getFirstName() + " " + member.getLastName());
			fxMemberIDError.setVisible(true);
			System.out.println("Data: " + member);
		}

	}

	public void init() {
		System.out.println("CheckoutBook init");
//		CheckoutBook.INSTANCE.setMaximized(true);
		CheckoutBook.INSTANCE.setTitle("Checkout Book");

	}
}
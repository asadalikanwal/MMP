package gui;

import java.io.IOException;
import java.util.List;
import business.AccessLevel;
import business.Book;
import business.Member;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardController {

	DataAccessFacade daf = new DataAccessFacade();

	@FXML
	private Tab TabMembers;

	@FXML
	private TableView<Member> tbMember;

	@FXML
	private TableColumn tbMemberName;

	@FXML
	private TableColumn tbMemberNum;

	@FXML
	private TableColumn tbMemberPhone;

	@FXML
	private TableColumn tbMemberStreet;

	@FXML
	private TableColumn tbMemberCity;

	@FXML
	private TableColumn tbMemberState;

	@FXML
	private TableColumn tbMemberZip;

	@FXML
	private Tab TabBooks;

	@FXML
	private TableView<Book> tbBook;

	@FXML
	private TableColumn tbBookId;

	@FXML
	private TableColumn tbBookTitle;

	@FXML
	private TableColumn tbBookISBN;

	@FXML
	private TableColumn tbBookAuthors;

	@FXML
	private TableColumn tbBookAvailability;

	@FXML
	private TableColumn tbBookAvailableCopy;

	@FXML
	private TableColumn tbBookTotalCopy;

	@FXML
	private TableColumn tbBookDuration;

	@FXML
	private MenuBar myMenuBar;

	@FXML
	private Menu menuMember;

	@FXML
	private Menu menuBook;

	@FXML
	private Menu menuCheckout;

	public void init() {
		Dashboard.INSTANCE.setMaximized(true);
		Dashboard.INSTANCE.setTitle("Library Management - Group 2");

		System.out.println("Dashboard Controller" + Root.getUserRole());

		// Making all the menu visibility false
		menuMember.setVisible(false);
		menuBook.setVisible(false);
		menuCheckout.setVisible(false);

		// Enable/Disable menu as per user accessLevel
		if (Root.getUserRole() == AccessLevel.LIBRARIAN) {
			menuCheckout.setVisible(true);
		} else if (Root.getUserRole() == AccessLevel.ADMINISTRATOR) {
			menuMember.setVisible(true);
			menuBook.setVisible(true);
		} else if (Root.getUserRole() == AccessLevel.BOTH) {
			menuMember.setVisible(true);
			menuBook.setVisible(true);
			menuCheckout.setVisible(true);
		}

		// Enable/Disable Tabs as per user accessLevel
		TabMembers.setDisable(false);
		TabBooks.setDisable(false);

		populateMemberTable();
		populateBookTable();
	}

	private void populateMemberTable() {
		List<Member> members = daf.readMember();

		tbMember.getItems().setAll(members);
		tbMemberName.setCellValueFactory(new PropertyValueFactory<Member, String>("fullName"));
		tbMemberNum.setCellValueFactory(new PropertyValueFactory<Member, String>("memberId"));
		tbMemberPhone.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNumber"));
		tbMemberStreet.setCellValueFactory(new PropertyValueFactory<Member, String>("street"));
		tbMemberCity.setCellValueFactory(new PropertyValueFactory<Member, String>("city"));
		tbMemberState.setCellValueFactory(new PropertyValueFactory<Member, String>("state"));
		tbMemberZip.setCellValueFactory(new PropertyValueFactory<Member, String>("zip"));

	}

	private void populateBookTable() {
		List<Book> books = daf.readBookList();

		tbBook.getItems().setAll(books);
		tbBookId.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
		tbBookTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		tbBookISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
		tbBookAuthors.setCellValueFactory(new PropertyValueFactory<Book, String>("authorsList"));
		tbBookAvailability.setCellValueFactory(new PropertyValueFactory<Book, String>("availability"));
		tbBookAvailableCopy.setCellValueFactory(new PropertyValueFactory<Book, String>("numberOfCopy"));
		tbBookTotalCopy.setCellValueFactory(new PropertyValueFactory<Book, String>("totalNumOfCopy"));
		tbBookDuration.setCellValueFactory(new PropertyValueFactory<Book, String>("maxCheckoutLength"));

	}

	public void addMemberEvent() throws IOException {
		System.out.println("addMemberEvent clicked");

		AddMember.INSTANCE.init(Root.rootStage());
		AddMember.INSTANCE.showAndWait();

	}

	public void editMemberEvent() throws IOException {
		System.out.println("EditMember clicked");

		EditMember.INSTANCE.init(Root.rootStage());
		EditMember.INSTANCE.showAndWait();

	}

	public void addBookEvent() throws IOException {
		System.out.println("addMemberEvent clicked");

		
		AddBook.INSTANCE.init(Root.rootStage());
		AddBook.INSTANCE.showAndWait();

	}
	

	public void addBookCopyEvent() throws IOException {
		System.out.println("adBookCopyEvent clicked");

		AddBookCopy.INSTANCE.init(Root.rootStage());
		AddBookCopy.INSTANCE.showAndWait();

	}

	@FXML
	public void checkoutBookEvent(ActionEvent event) throws IOException {

		System.out.println("Checkoutbook - clicked");
		CheckoutBook.INSTANCE.init(Root.rootStage());
		CheckoutBook.INSTANCE.showAndWait();

	}

	public void addAuthorEvent(ActionEvent event) throws IOException {

		System.out.println("AddAuthor - clicked");
		AddAuthor.INSTANCE.init(Root.rootStage());
		AddAuthor.INSTANCE.showAndWait();

	}

}
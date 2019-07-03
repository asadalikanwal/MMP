package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import business.AccessLevel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class DashboardController{
	
	@FXML
	MenuBar myMenuBar;
	
	@FXML
	private Menu menuMember;
	
	@FXML
	private Menu menuBook;
	
	@FXML
	private Menu menuCheckout;
	
	@FXML
	private Tab TabMembers;
	
	@FXML
	private Tab TabBooks;
	
	public void init() {
		Dashboard.INSTANCE.setMaximized(true);
		Dashboard.INSTANCE.setTitle("Library Management - Group 2");
		
		System.out.println(Root.getUserRole());
		
		// Making all the menu visibility false
		menuMember.setVisible(false);
		menuBook.setVisible(false);
		menuCheckout.setVisible(false);
		
		// Enable/Disable menu as per user accessLevel
		if(Root.getUserRole() == AccessLevel.LIBRARIAN) {
			menuCheckout.setVisible(true);
		} else if(Root.getUserRole() == AccessLevel.ADMINISTRATOR) {
			menuMember.setVisible(true);
			menuBook.setVisible(true);
		} else if(Root.getUserRole() == AccessLevel.BOTH) {
			menuMember.setVisible(true);
			menuBook.setVisible(true);
			menuCheckout.setVisible(true);
		}
		
		// Enable/Disable Tabs as per user accessLevel
		TabMembers.setDisable(false);
		TabBooks.setDisable(false);
	}
	
	public  void addMemberEvent() throws IOException {
		System.out.println("addMemberEvent clicked");
		
		AddMember.INSTANCE.init(Root.rootStage());
		AddMember.INSTANCE.showAndWait();
		
	}
	
	
	public  void editMemberEvent() throws IOException {
		System.out.println("EditMember clicked");
		
		EditMember.INSTANCE.init(Root.rootStage());
		EditMember.INSTANCE.showAndWait();
		
	}
	
	public void addBookCopyEvent()  throws IOException {
		AddBookCopy.INSTANCE.init(Root.rootStage());
		AddBookCopy.INSTANCE.showAndWait();
	}
	
//	public  void addBookEvent() throws IOException {
//		System.out.println("addMemberEvent clicked");
//		
//		AddBook.INSTANCE.init(Root.rootStage());
//		AddBook.INSTANCE.showAndWait();
//		
//	}
	
	
//	public  void addBookCopyEvent() throws IOException {
//	System.out.println("addMemberEvent clicked");
//	
//	AddBookCopy.INSTANCE.init(Root.rootStage());
//	AddBookCopy.INSTANCE.showAndWait();
//	
//}

	
	
	
	
//	public  void addMemberEvent() throws IOException {
//		System.out.println("addMemberEvent clicked");
//		
////		Parent addMember = FXMLLoader.load(getClass().getResource("AddMember.fxml"));
////		Scene memberScene = new Scene(addMember);
////		
////		
//////		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
////		//In your event handler
////		Stage window = (Stage) myMenuBar.getScene().getWindow();
////		window.setScene(memberScene);
////		window.show();
//		
//		AddMember.INSTANCE.init(Root.rootStage());
//		AddMember.INSTANCE.showAndWait();
//		
//		
//	}
	
	
//	public  void addMemberBttnEvent(ActionEvent event) throws IOException {
//		System.out.println("addMemberEvent clicked");
//
//		
//		Parent addMember = FXMLLoader.load(getClass().getResource("AddMember.fxml"));
//		Scene memberScene = new Scene(addMember);
//		
//		
//		
//		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//		window.setScene(memberScene);
//		window.show();
//	}

//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		// TODO Auto-generated method stub
//		System.out.println("Initialize");
//		//menuMember.setVisible(false);
//		
//	}
	

}
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class DashboardController implements Initializable {
	
	@FXML
	MenuBar myMenuBar;
	
//	@FXML
//	Menu mnuMember;
	
	@FXML
	private Menu mnuMember;
	
	
	@FXML
	private Tab TabMembers;
	
	@FXML
	private Tab TabBooks;
//	
	public void init() {
		Dashboard.INSTANCE.setMaximized(true);
		Dashboard.INSTANCE.setTitle("Library Management - Group 2");
		
//		mnuMember.setVisible(false);
//		if (loggedInRole == Admin)
		TabMembers.setDisable(false);
		TabBooks.setDisable(false);
	}
	
	public  void addMemberEvent() throws IOException {
		System.out.println("addMemberEvent clicked");
		
//		Parent addMember = FXMLLoader.load(getClass().getResource("AddMember.fxml"));
//		Scene memberScene = new Scene(addMember);
//		
//		
////		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//		//In your event handler
//		Stage window = (Stage) myMenuBar.getScene().getWindow();
//		window.setScene(memberScene);
//		window.show();
		
		AddMember.INSTANCE.init(Root.rootStage());
		AddMember.INSTANCE.showAndWait();
		
		
	}
	
	
	public  void addMemberBttnEvent(ActionEvent event) throws IOException {
		System.out.println("addMemberEvent clicked");

		
		Parent addMember = FXMLLoader.load(getClass().getResource("AddMember.fxml"));
		Scene memberScene = new Scene(addMember);
		
		
		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(memberScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("Initialize");
		//mnuMember.setVisible(false);
		
	}
	

}
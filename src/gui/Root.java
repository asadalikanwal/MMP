package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Root extends Application {
	
	private static Stage rootStage = null;
	private LoginController loginController;

	public static Stage rootStage() {
		return rootStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		rootStage = primaryStage;
		

		
		
//		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
//		Parent dashboard = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
//		Parent addMember = FXMLLoader.load(getClass().getResource("AddMember.fxml"));
//		Parent addBook = FXMLLoader.load(getClass().getResource("AddBook.fxml"));
//		Parent addBookCopy = FXMLLoader.load(getClass().getResource("addBookCopy.fxml"));
		primaryStage.setTitle("Library Management System - Group 2");
		

		
//		primaryStage.setScene(new Scene(dashboard));
//		primaryStage.show();
		
		Parent rootLoginWindow = null;
		
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
//		rootLoginWindow = loader.load();
//
//		loginController = loader.getController();
//		loginController.setApp(this);
//		
//		
//		Scene sceneLogin = new Scene(rootLoginWindow);
//		primaryStage.setScene(sceneLogin);
//		primaryStage.show();
		
		
		
		
		Login.INSTANCE.init(primaryStage);
		Login.INSTANCE.show();

	}
	

	
	
}
package gui;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class LoginController  {
	
	private Root currentStage;
	
	public void init() {
		Login.INSTANCE.setTitle("Library Management - Group 2");
	}
	
	public void setApp(Root root) {
		currentStage = root;
	}
	
	public void loginButtonClicked() {
		System.out.println("Logged in!");
		
		Dashboard.INSTANCE.init(Root.rootStage());
		Dashboard.INSTANCE.show();
		Login.INSTANCE.hide();
		//currentStage.showMainScreen();
		
		
		
	}
}
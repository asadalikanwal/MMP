package gui;

import business.AccessLevel;
import dataaccess.DataAccessFacade;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class LoginController {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Label errorMsg;

	private Root currentStage;

	public void init() {
		Login.INSTANCE.setTitle("Library Management - Group 2");
		errorMsg.setVisible(false);
	}

	public void setApp(Root root) {
		currentStage = root;
	}

	public void loginButtonClicked() {
		DataAccessFacade daf = new DataAccessFacade();
		AccessLevel roleString = daf.userLogin(username.getText(), password.getText());
		if (roleString.equals(AccessLevel.NONE)) {
			errorMsg.setText("Username or password is not valid!");
			errorMsg.setVisible(true);
			return;
		}

		Root.setUserRole(roleString);
		Dashboard.INSTANCE.init(Root.rootStage());
		Dashboard.INSTANCE.show();
		Login.INSTANCE.hide();
	}
}
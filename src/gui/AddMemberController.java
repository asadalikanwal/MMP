package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Address;
import business.Member;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class AddMemberController {

	@FXML
	private TextField fxFirstName;

	@FXML
	private TextField fxLastName;

	@FXML
	private TextField fxStreet;

	@FXML
	private TextField fxCity;

	@FXML
	private TextField fxState;

	@FXML
	private TextField fxZip;

	@FXML
	private TextField fxTel;

	@FXML
	private Label fxFirstNameError;

	@FXML
	private Label fxLastNameError;

	@FXML
	private Label fxStreetError;

	@FXML
	private Label fxCityError;

	@FXML
	private Label fxStateError;

	@FXML
	private Label fxZipError;

	@FXML
	private Label fxTelError;

	@FXML
	void addMemberClick(ActionEvent event) {

		// Form Validations
		boolean firstName = Validation.isValid(fxFirstName.getText(), "String", fxFirstNameError);
		boolean lastName = Validation.isValid(fxLastName.getText(), "String", fxLastNameError);
		boolean street = Validation.isValid(fxStreet.getText(), "String", fxStreetError);
		boolean city = Validation.isValid(fxCity.getText(), "String", fxCityError);
		boolean state = Validation.isValid(fxState.getText(), "String", fxStateError);
		boolean zip = Validation.isValid(fxZip.getText(), "Number", fxZipError);
		boolean telNumber = Validation.isValid(fxTel.getText(), "Number", fxTelError);

		if (!firstName || !lastName || !street || !city || !state || !zip || !telNumber) {
			return;
		}

		DataAccessFacade daf = new DataAccessFacade();

		Address address = new Address(fxStreet.getText(), fxCity.getText(), fxZip.getText(), fxState.getText());
		Member member = new Member(fxFirstName.getText(), fxLastName.getText(), address, fxTel.getText());

		//daf.saveNewMember(member);
		AddMember.INSTANCE.hide();

    	HashMap<String, Member> result = (HashMap<String, Member>) daf.readMemberMap();
		for (Member entry : result.values()) {
			System.out.println(entry.toString());
		}
	}

	public void init() {
//		AddMember.INSTANCE.setMaximized(true);
		AddMember.INSTANCE.setTitle("Add Member");

	}
}
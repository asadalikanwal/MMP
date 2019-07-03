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

public class EditMemberController {

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
	private TextField fxMemberID;
	
	@FXML
	private Label fxMemberIDError;

	@FXML
	void EditMemberClick(ActionEvent event) {

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

//		Address address = new Address(fxStreet.getText(), fxCity.getText(), fxZip.getText(), fxState.getText());
//		Member member = new Member(fxFirstName.getText(), fxLastName.getText(), address, fxTel.getText());
		
		Member member = daf.srcMember(fxMemberID.getText());


		daf.updateMembers(member);
		EditMember.INSTANCE.hide();

		HashMap<String, Member> result = (HashMap<String, Member>) daf.getMembers();
		for (Member entry : result.values()) {
			System.out.println(entry.toString());
		}
	}
	
	@FXML
    void searchMember(ActionEvent event) {
		boolean memberCheck = Validation.isValid(fxMemberID.getText(), "Number", fxMemberIDError);
		
		if (!memberCheck) {
			return;
		}
		
		System.out.println("Search Member : "+fxMemberID.getText());
		
		DataAccessFacade daf = new DataAccessFacade();
		Member member = daf.srcMember(fxMemberID.getText());

		if(member == null) {
			fxMemberIDError.setText("Invalid Member ID");
			fxMemberIDError.setVisible(true);
			System.out.println("Data: "+member);
		} else {
			fxMemberIDError.setVisible(false);
		}
		
		fxFirstName.setText(member.getFirstName());
		fxLastName.setText(member.getLastName());
		fxStreet.setText(member.getAddress().getStreet());
		fxCity.setText(member.getAddress().getCity());
		fxState.setText(member.getAddress().getState());
		fxZip.setText(member.getAddress().getZip());
		fxTel.setText(member.getPhoneNumber());
		
		
    }

	public void init() {
		System.out.println("Edit init");
//		EditMember.INSTANCE.setMaximized(true);
		EditMember.INSTANCE.setTitle("Edit Member");

	}
}
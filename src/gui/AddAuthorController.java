package gui;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Address;
import business.Author;
import business.Member;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class AddAuthorController {

    @FXML
    private TextField fxFirstName;

    @FXML
    private TextField fxLastName;

    @FXML
    private Label fxTelError;

    @FXML
    private Label fxStateError;

    @FXML
    private TextField fxStreet;

    @FXML
    private Label fxStreetError;

    @FXML
    private TextField fxCity;

    @FXML
    private Label fxCityError;

    @FXML
    private Label fxZipError;

    @FXML
    private TextField fxCredentials;

    @FXML
    private TextField fxState;

    @FXML
    private Label fxFirstNameError;

    @FXML
    private TextField fxShortBio;

    @FXML
    private TextField fxZip;

    @FXML
    private TextField fxTel;

    @FXML
    private Label fxLastNameError;


   
	  



	@FXML
	void addAuthorClick(ActionEvent event) {

		// Form Validations
		boolean firstName = Validation.isValid(fxFirstName.getText(), "String", fxFirstNameError);
		boolean lastName = Validation.isValid(fxLastName.getText(), "String", fxLastNameError);
		
		boolean street = Validation.isValid(fxStreet.getText(), "String", fxStreetError);
		boolean city = Validation.isValid(fxCity.getText(), "String", fxCityError);
		boolean state = Validation.isValid(fxState.getText(), "String", fxStateError);
		boolean zip = Validation.isValid(fxZip.getText(), "Number", fxZipError);
		boolean telNumber = Validation.isValid(fxTel.getText(), "Number", fxTelError);

		if (!firstName || !lastName || !street || !city || !state || !zip || !telNumber ) {
			return;
		}

		DataAccessFacade daf = new DataAccessFacade();

		Address address = new Address(fxStreet.getText(), fxCity.getText(), fxZip.getText(), fxState.getText());
		Author author = new Author(fxFirstName.getText(), fxLastName.getText(), address, fxTel.getText(), fxShortBio.getText(), fxCredentials.getText());
		
		//Author(String firstName, String lastName, Address address, String phoneNumber, String shortBio, String credentials) 
		

		System.out.println(author.getFirstName());
		AddAuthor.INSTANCE.hide();

    	
	}

	public void init() {
//		AddAuthor.INSTANCE.setMaximized(true);
		AddAuthor.INSTANCE.setTitle("Add Member");

	}
}
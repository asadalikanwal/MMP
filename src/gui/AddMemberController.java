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

public class AddMemberController  {
	
	@FXML
    private TextField fxMemberID;

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
    private Label fxMemberIDLabel;

    @FXML
    void addMemberClick(ActionEvent event) {
//		Member member1 = new Member("Jepeng", "Gao", new Address("1000 N. 4th St.", "Fairfield", "52557", "Iowa"),
//		"12345567", "100");
    	
    	
    	 if(isValidString(fxFirstName.getText())){
    		 System.out.println("ERROR with firstname");
    		 fxMemberIDLabel.setVisible(true);
    		 return;
    	 }
    	
    	
    	DataAccessFacade daf = new DataAccessFacade();
    	
    	Address address = new Address(fxStreet.getText(), fxCity.getText(), fxZip.getText(), fxState.getText());
    	Member member1 = new Member(fxFirstName.getText(), fxLastName.getText(), address ,
    			"12345567");
    	
    	daf.saveNewMember(member1);
    	
    	
//    	HashMap<String, Member> members = daf.readMemberMap();
    	HashMap<String, Member> result = (HashMap<String, Member>) daf.readMemberMap();
		for (Member entry : result.values()) {
			System.out.println(entry.toString());
		}
    }
    
    private boolean isValidString(String s){  
    	if(s.isEmpty()) return false;
        String regex="[A-Za-z\\s]+";      
         return s.matches(regex);//returns true if input and regex matches otherwise false;
    }
    
	public void init() {
//		AddMember.INSTANCE.setMaximized(true);
		AddMember.INSTANCE.setTitle("Add Member");
		
		
		fxMemberIDLabel.setVisible(false);
		

	}
}
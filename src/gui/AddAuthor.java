package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AddAuthor extends Stage {
	public static final AddAuthor INSTANCE = new AddAuthor();
	public static AddAuthorController Controller;
	
	public void init(Window owner) {
		if(AddAuthor.INSTANCE.getOwner() == null) AddAuthor.INSTANCE.initOwner(owner);
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAuthor.fxml"));
			Scene scene = new Scene(loader.load());
			Controller = loader.getController();
			
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			resizableProperty().setValue(Boolean.FALSE);
			setScene(scene);
			
			if(getModality() != Modality.APPLICATION_MODAL) initModality(Modality.APPLICATION_MODAL);
			
			Controller.init();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

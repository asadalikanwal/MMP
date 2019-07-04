package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CheckoutBook extends Stage {
	public static final CheckoutBook INSTANCE = new CheckoutBook();
	public static CheckoutBookController Controller;
	
	public void init(Window owner) {
		if(CheckoutBook.INSTANCE.getOwner() == null) CheckoutBook.INSTANCE.initOwner(owner);
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckoutBook.fxml"));
			Scene scene = new Scene(loader.load(), 823, 540);
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

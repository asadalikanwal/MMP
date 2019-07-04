package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AddBookCopy extends Stage {
	public static final AddBookCopy INSTANCE = new AddBookCopy();
	public static AddBookCopyController Controller;

	public void init(Window owner) {
		if (AddBookCopy.INSTANCE.getOwner() == null)
			AddBookCopy.INSTANCE.initOwner(owner);

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBookCopy.fxml"));
			Scene scene = new Scene(loader.load());
			Controller = loader.getController();

			resizableProperty().setValue(Boolean.FALSE);
			setScene(scene);

			if (getModality() != Modality.APPLICATION_MODAL)
				initModality(Modality.APPLICATION_MODAL);

			Controller.init();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

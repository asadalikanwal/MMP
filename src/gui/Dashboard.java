package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Dashboard extends Stage {
	public static final Dashboard INSTANCE = new Dashboard();
	public static DashboardController Controller;

	public void init(Window owner) {
		if (Dashboard.INSTANCE.getOwner() == null)
			Dashboard.INSTANCE.initOwner(owner);

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
			Scene scene = new Scene(loader.load());
			Controller = loader.getController();

			setScene(scene);

			Controller.init();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

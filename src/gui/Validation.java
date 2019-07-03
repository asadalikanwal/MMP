package gui;

import javafx.scene.control.Label;

public class Validation {
	static boolean isValid(String s, String type, Label label) {
		String regex = null;

		if (type.equals("String")) {
			regex = "[A-Za-z\\s]+";
			label.setText("Please add the valid string");
		} else if (type.equals("Address")) {
			if (s.isEmpty() || s == null || s == "") {
				label.setText("Please add the valid Address");
				label.setVisible(true);
				return false;
			} else {
				label.setVisible(false);
			}
		} else {
			regex = "[0-9]+";
			label.setText("Please add the valid number");
		}

		if (s.isEmpty() || !s.matches(regex)) {
			label.setVisible(true);
			return false;
		} else {
			label.setVisible(false);
		}

		return true;
	}
}

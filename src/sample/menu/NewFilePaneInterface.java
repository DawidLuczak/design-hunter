package sample.menu;

import javafx.scene.control.TextField;

public interface NewFilePaneInterface {

	static boolean checkSizeInput(TextField field){
		if (field.getText().isBlank()){
			return false;
		} else {
			try {
				Integer.parseInt(field.getText());
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		}
	}
	
	static void sizeInputLimiter(TextField field, int length){
		field.textProperty().addListener((observableValue, s, t1) -> {
			if (field.getText().length() > length) {
				field.setText(field.getText().substring(0, length));
			}
		});
	}

}

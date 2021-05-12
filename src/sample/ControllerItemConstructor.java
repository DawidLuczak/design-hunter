package sample;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public interface ControllerItemConstructor {
	
	static void constructDoubleSpinner(Spinner<Double> spinner, double min, double max, double step){
		SpinnerValueFactory.DoubleSpinnerValueFactory dsvf = new SpinnerValueFactory.DoubleSpinnerValueFactory(min, max);
		dsvf.setAmountToStepBy(step);
		spinner.setValueFactory(dsvf);
		spinner.getEditor().setOnAction(actionEvent -> {
			try {
				double newNumber = Double.parseDouble(spinner.getEditor().getText());
				if (newNumber > max) {
					spinner.getValueFactory().setValue(max);
				} else spinner.getValueFactory().setValue(Math.max(newNumber, min));
			} catch (NumberFormatException e){
				spinner.getEditor().setText(spinner.getValueFactory().getValue().toString());
			}
		});
	}
	
}

package sample.auxiliary;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import sample.Controller;

public record ChangeWorkspace
		(
				Controller controller
		)
		implements ChangeListener<Number>
{
	
		@Override
	public void changed
			(
					ObservableValue<? extends Number> observableValue,
					Number number,
					Number t1
			)
	{
		controller.setActualWorkspace(
				controller.getWorkspaceList().get(
						t1.intValue()
				)
		);
		ControllerInterface.updateLayers(
				controller
		);
		controller.setWorkspaceToolsProperties(
				controller.getActualWorkspace()
		);
	}
}
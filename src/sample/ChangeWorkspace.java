package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import sample.workspace.MouseDraftsmanInterface;

public record ChangeWorkspace(Controller controller) implements ChangeListener<Number> {
	
	@Override
	public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
		controller.setActiveWorkspace(controller.getWorkspaceList().get(t1.intValue()));
		MouseDraftsmanInterface.setMouseDrawing(controller);
		ControllerInterface.updateLayers(controller);
		controller.setWorkspaceToolsProperties(controller.getActiveWorkspace());
	}
}
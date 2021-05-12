package sample;

import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import sample.workspace.Layer;
import sample.workspace.MouseDraftsmanInterface;
import sample.workspace.Workspace;

import java.util.ArrayList;

public interface ControllerInterface {
	
	static void addToWorkspaceList(Controller controller, Workspace workspace){
		if (controller.getWorkspaceList() == null)
			controller.setWorkspaceList(new ArrayList<>());
		
		controller.getWorkspaceList().add(workspace);
		controller.getTabPanel().getTabs().add(new Tab(workspace.getTitle(), workspace.getWorkspaceContent().getStackPane()));
		controller.getTabPanel().getSelectionModel().selectLast();
		controller.setActiveWorkspace(workspace);
		
		controller.setWorkspaceToolsProperties(workspace);
		MouseDraftsmanInterface.setMouseDrawing(controller);
	}
	
	static void updateLayers(Controller controller){
		controller.getLayerList().setItems(controller.getActiveWorkspace().getWorkspaceContent().getLayers());
		controller.getLayerList().refresh();
	}
	
	static void showLayer(Workspace workspace, ListView<Layer> listView){
		workspace.getWorkspaceContent().showLayer(listView.getSelectionModel().getSelectedIndex());
	}
	
	static void layerViewList(Controller controller){
		controller.getLayerList().setCellFactory(canvasListView -> new LayersListView());
		controller.getLayerList().getSelectionModel().selectedIndexProperty().addListener(
				(ov, oldVal, newVal) -> {
					ControllerInterface.showLayer(controller.getActiveWorkspace(), controller.getLayerList());
					HelpfulInterface.swapValues(oldVal, newVal);
				});
	}
}




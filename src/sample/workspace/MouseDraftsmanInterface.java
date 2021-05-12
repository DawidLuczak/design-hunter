package sample.workspace;

import sample.Controller;

public interface MouseDraftsmanInterface {
	
	static void setMouseDrawing(Controller controller){
		controller.setMouseEvents(new MouseDraftsmanEvents(controller.getActiveWorkspace()));
	}
}

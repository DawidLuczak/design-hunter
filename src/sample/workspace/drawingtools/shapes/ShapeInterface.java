package sample.workspace.drawingtools.shapes;

import sample.Controller;

public interface ShapeInterface {
	
	static void setShape(Controller controller, Shapes shape){
		if (controller.getActiveWorkspace() != null) {
			controller.getActiveWorkspace().getWorkspaceContent().setShape(shape);
			if (shape.shouldEventOnDrag) {
				controller.getMouseDraftsmanEvents().startUsingBrush();
			} else {
				controller.getMouseDraftsmanEvents().stopUsingBrush();
			}
		}
	}
}

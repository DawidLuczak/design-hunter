package sample.workspace.drawingtools.shapes;

import sample.Controller;

public interface ShapeInterface {
	
	static void setShape(Controller controller, Shapes shape) {
		if (controller.getActiveWorkspace() != null) {
			
			if (controller.getActiveWorkspace().getWorkspaceContent().getShape() != null &&
							controller.getActiveWorkspace().getWorkspaceContent().getShape().shouldEventOnDrag) {
				controller.getMouseDraftsmanEvents().stopUsingBrush();
			}
			
			controller.getActiveWorkspace().getWorkspaceContent().setShape(shape);
			
			if (shape.shouldEventOnDrag) {
				controller.getMouseDraftsmanEvents().startUsingBrush();
			}
		}
	}
}

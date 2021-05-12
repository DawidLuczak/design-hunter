package sample.workspace;

import javafx.scene.input.MouseEvent;
import sample.workspace.drawingtools.Brush;
import sample.workspace.drawingtools.DrawingTools;
import sample.workspace.drawingtools.Point;

public class MouseDraftsmanEvents implements DrawingTools {
	
	private final Point pointA, pointB;
	private Workspace workspace;
	
	MouseDraftsmanEvents(Workspace workspace) {
		setWorkspace(workspace);
		pointA = new Point(0, 0);
		pointB = new Point(0, 0);
	}
	
	private void mousePressed(MouseEvent event) {
		pointA.setX(event.getX());
		pointA.setY(event.getY());
	}
	
	private void mouseReleased(MouseEvent event) {
		pointB.setX(event.getX());
		pointB.setY(event.getY());
		
		if (pointB.getX() < pointA.getX() || pointB.getY() < pointA.getY()){
			pointA.swap(pointB);
		}
		
		DrawingTools.draw(
				workspace.getWorkspaceContent().getActiveLayer().getGraphicsContext(),
				workspace.getWorkspaceContent().getShape(),
				workspace.getWorkspaceContent().getFillType(),
				pointA, pointB
		);
	}
	
	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
		addClickingListeners();
	}
	
	private void addClickingListeners() {
		this.workspace.getWorkspaceContent().getStackPane().setOnMousePressed(this::mousePressed);
		this.workspace.getWorkspaceContent().getStackPane().setOnMouseReleased(this::mouseReleased);
	}
	
	@Override
	public void startUsingBrush() {
		workspace.getWorkspaceContent().getStackPane().setOnMouseDragged(event -> Brush.mouseDragged(event, workspace));
		workspace.getWorkspaceContent().getStackPane().setOnMousePressed(event -> Brush.mouseDragEnter(workspace));
		workspace.getWorkspaceContent().getStackPane().setOnMouseReleased(event -> Brush.mouseDragExit(workspace));
	}
	
	@Override
	public void stopUsingBrush() {
		workspace.getWorkspaceContent().getStackPane().setOnMouseDragged(null);
		addClickingListeners();
	}

}

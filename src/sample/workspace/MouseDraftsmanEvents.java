package sample.workspace;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import sample.workspace.drawingtools.Brush;
import sample.workspace.drawingtools.DrawingTools;
import sample.workspace.drawingtools.Pixel;
import sample.workspace.drawingtools.Point;

public class MouseDraftsmanEvents implements DrawingTools {
	
	private Point pointA, pointB;
	private Workspace workspace;
	private Canvas activeContent;
	private boolean drawing;
	
	MouseDraftsmanEvents(Workspace workspace) {
		setWorkspace(workspace);
		pointA = new Point(0, 0);
		pointB = new Point(0, 0);
		drawing = false;
	}
	
	private void startDrawing(MouseEvent event){
		pointA.setX(event.getX());
		pointA.setY(event.getY());
		drawing = true;
	}
	
	private void mousePressed(MouseEvent event) {
		if (event.isPrimaryButtonDown()) {
			if (event.isControlDown()){
				getCreatedContent(event);
			} else {
				startDrawing(event);
			}
		} else if (event.isSecondaryButtonDown()) {
			workspace.getWorkspaceContent().getActiveLayer().setActiveContent(null);
		}
	}
	
	private void getCreatedContent(MouseEvent event){
		ObservableList<Node> container = workspace.getWorkspaceContent().getActiveLayer().getStackPane().getChildren();
		if (event.isAltDown()) {
			container.get(container.size() - 1).toBack();
		} else {
			for (int i = container.size() - 1; i > 0; i--) {
				if (container.get(i).contains(event.getX(), event.getY())) {
					activeContent = workspace.getWorkspaceContent().getActiveLayer().getObject(i);

					workspace.getWorkspaceContent().getActiveLayer().setActiveContent(activeContent);
					return;
				}
			}
		}
	}
	
	private void mouseReleased(MouseEvent event) {
		pointB.setX(event.getX());
		pointB.setY(event.getY());
		if (event.getX() < pointA.getX()) {
			pointB.setX(pointA.getX());
			pointA.setX(event.getX());
		}
		if (event.getY() < pointA.getY()) {
			pointB.setY(pointA.getY());
			pointA.setY(event.getY());
		}
		
		draw();
	}
	
	private void draw(){
		if (drawing) {
			activeContent = ContentInterface.newContent(workspace);
			
			workspace.getWorkspaceContent().getActiveLayer().addContentElement(activeContent);
			DrawingTools.draw(workspace.getWorkspaceContent(), pointA, pointB);
			drawing = false;
		}
	}
	
	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
		addClickingListeners();
	}
	
	public void addClickingListeners() {
		workspace.getWorkspaceContent().getStackPane().setOnMousePressed(this::mousePressed);
		workspace.getWorkspaceContent().getStackPane().setOnMouseReleased(this::mouseReleased);
	}
	
	public void getColorsMouseListeners(){
		workspace.getWorkspaceContent().getStackPane().setOnMousePressed(event -> {
			if (event.isPrimaryButtonDown()) {
				workspace.getWorkspaceContent().setColorFirst(Pixel.getColor(workspace.getWorkspaceContent().getActiveLayer().getActiveContent(),
						event.getX(), event.getY()));
				System.out.println(workspace.getWorkspaceContent().getColorFirst());
			} else if (event.isSecondaryButtonDown()){
				workspace.getWorkspaceContent().setColorSecondary(Pixel.getColor(workspace.getWorkspaceContent().getActiveLayer().getActiveContent(),
						event.getX(), event.getY()));
			}
		});
		workspace.getWorkspaceContent().getStackPane().setOnMouseReleased(null);
	}
	
	@Override
	public void startUsingBrush() {
		activeContent = new Canvas();
		ContentInterface.setGraphicsContent(activeContent, workspace);
		workspace.getWorkspaceContent().getActiveLayer().setActiveContent(activeContent);
		
		workspace.getWorkspaceContent().getStackPane().setOnMouseDragged(event -> Brush.mouseDragged(event, activeContent));
		workspace.getWorkspaceContent().getStackPane().setOnMousePressed(event -> Brush.mouseDragEnter(activeContent));
		workspace.getWorkspaceContent().getStackPane().setOnMouseReleased(event -> Brush.mouseDragExit(activeContent));
	}
	
	@Override
	public void stopUsingBrush() {
		workspace.getWorkspaceContent().getStackPane().setOnMouseDragged(null);
		if (activeContent != null && !workspace.getWorkspaceContent().getActiveLayer().getStackPane().getChildren().contains(activeContent))
			workspace.getWorkspaceContent().getActiveLayer().addContentElement(activeContent);
		addClickingListeners();
	}
	
}

package sample.workspace;


import javafx.scene.canvas.Canvas;

public interface ContentInterface {
	
	static Canvas newContent(Workspace workspace){
		Canvas canvas = new Canvas(workspace.getWidth(), workspace.getHeight());
		setGraphicsContent(canvas, workspace);
		return canvas;
	}
	
	static void setGraphicsContent(Canvas content, Workspace workspace){
		content.getGraphicsContext2D().setFill(workspace.getWorkspaceContent().getColorFirst());
		content.getGraphicsContext2D().setStroke(workspace.getWorkspaceContent().getColorSecondary());
		content.getGraphicsContext2D().setLineWidth(workspace.getWorkspaceContent().getLineWidth());
	}
}

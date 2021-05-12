package sample.workspace.drawingtools;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import sample.workspace.Workspace;
import sample.workspace.drawingtools.Point;

public interface Brush {
	
	static void mouseDragEnter(Workspace workspace){
		workspace.getWorkspaceContent().getActiveLayer().getGraphicsContext().beginPath();
	}
	
	static void mouseDragExit(Workspace workspace){
		workspace.getWorkspaceContent().getActiveLayer().getGraphicsContext().closePath();
	}
	
	static void mouseDragged(MouseEvent event, Workspace workspace) {
		workspace.getWorkspaceContent().getActiveLayer().getGraphicsContext().lineTo(event.getX(), event.getY());
		workspace.getWorkspaceContent().getActiveLayer().getGraphicsContext().stroke();
	}
	
	void startUsingBrush();
	
	void stopUsingBrush();
}

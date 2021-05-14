package sample.workspace.drawingtools;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public interface Brush {
	
	static void mouseDragEnter(Canvas content){
		content.getGraphicsContext2D().beginPath();
	}
	
	static void mouseDragExit(Canvas content){
		content.getGraphicsContext2D().closePath();
	}
	
	static void mouseDragged(MouseEvent event, Canvas content) {
		content.resize(content.getLayoutX() + event.getX(), content.getLayoutY() + event.getY());
		content.getGraphicsContext2D().lineTo(event.getX(), event.getY());
		content.getGraphicsContext2D().stroke();
	}
	
	void startUsingBrush();
	
	void stopUsingBrush();
}

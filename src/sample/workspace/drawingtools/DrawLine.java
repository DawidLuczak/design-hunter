package sample.workspace.drawingtools;

import javafx.scene.canvas.GraphicsContext;

public interface DrawLine {
	
	static void drawLine(GraphicsContext graphicsContext, Point pointA, Point pointB){
		graphicsContext.strokeLine(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
	}
	
	static void drawLineTo(GraphicsContext graphicsContext, Point point){
		graphicsContext.lineTo(point.getX(), point.getY());
		graphicsContext.stroke();
	}
}

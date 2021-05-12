package sample.workspace.drawingtools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import sample.workspace.drawingtools.shapes.FillType;

public interface DrawRectangle {
	
	static void rectangleFill(GraphicsContext graphicsContext, Rectangle rectangle){
		graphicsContext.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
	}
	
	static void rectangleStroke(GraphicsContext graphicsContext, Rectangle rectangle){
		graphicsContext.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
	}
	
	static void drawRectangle(GraphicsContext graphicsContext, Point pointA, Point pointB, FillType fillType){
		if (fillType == null)
			fillType = FillType.FULL;
		
		Rectangle rectangle = new Rectangle(pointA.getX(), pointA.getY(), pointA.distanceX(pointB), pointA.distanceY(pointB));
		
		if (fillType.equals(FillType.FILL)) {
			rectangleFill(graphicsContext, rectangle);
		} else if (fillType.equals(FillType.STROKE)) {
			rectangleStroke(graphicsContext, rectangle);
		} else {
			rectangleFill(graphicsContext, rectangle);
			rectangleStroke(graphicsContext, rectangle);
		}
	}
	
}
